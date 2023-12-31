package International_Trade_Union.model;



import International_Trade_Union.config.BLockchainFactory;
import International_Trade_Union.config.BlockchainFactoryEnum;
import International_Trade_Union.controllers.BasisController;
import International_Trade_Union.entity.DtoTransaction.DtoTransaction;
import International_Trade_Union.entity.blockchain.Blockchain;
import International_Trade_Union.entity.blockchain.block.Block;
import International_Trade_Union.governments.Directors;
import International_Trade_Union.governments.UtilsGovernment;
import International_Trade_Union.setings.Seting;
import International_Trade_Union.utils.base.Base;
import International_Trade_Union.utils.base.Base58;
import International_Trade_Union.vote.*;
import International_Trade_Union.utils.*;

import java.io.File;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.*;
import java.util.stream.Collectors;

import static International_Trade_Union.setings.Seting.SPECIAL_FORK_BALANCE;

public class Mining {
    public static boolean miningIsObsolete = false;
    private static volatile boolean isMiningStop = false;
    public static Blockchain getBlockchain(String filename, BlockchainFactoryEnum factoryEnum) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, NoSuchProviderException, InvalidKeyException {

        List<Block> blocks = UtilsBlock.readLineObject(filename);
        Blockchain blockchain = null;
        blockchain = BLockchainFactory.getBlockchain(factoryEnum);

        if (blocks.size() != 0) {
           blockchain.setBlockchainList(blocks);
        }
        return blockchain;
    }

    public static boolean isIsMiningStop() {
        return isMiningStop;
    }

    public static synchronized void setIsMiningStop(boolean isMiningStop) {

        Mining.isMiningStop = isMiningStop;
    }

    public static Map<String, Account> getBalances(String filename, Blockchain blockchain, Map<String, Account> balances, List<String> signs) throws IOException, NoSuchAlgorithmException, SignatureException, InvalidKeySpecException, NoSuchProviderException, InvalidKeyException {
        //start test


        //папка чтобы проверить есть ли
        File folder = new File(filename);
        List<String> files = new ArrayList<>();
        for (File file : folder.listFiles()) {
            if(!file.isDirectory()){
                files.add(file.getAbsolutePath());
            }
        }

        if (files.size() > 0 ){
            File file = new File(files.get(files.size()-1));
            if(file.exists() && file.length() > 0){
                balances = SaveBalances.readLineObject(filename);
            }

        }

        if (balances == null) {
            balances = new HashMap<>();
        }

        Block block;
        if(blockchain != null && blockchain.sizeBlockhain() > 0){
            block = blockchain.getBlock(blockchain.sizeBlockhain() - 1);
            balances = UtilsBalance.calculateBalance(balances, block, signs);
            //test
//получает все созданные когда либо законы
            Map<String, Laws> allLaws = UtilsLaws.getLaws(blockchain.getBlockchainList().subList(
                    blockchain.sizeBlockhain()-Seting.LAW_MONTH_VOTE, blockchain.sizeBlockhain()
            ), Seting.ORIGINAL_ALL_CORPORATION_LAWS_FILE);

            //возвращает все законы с голосами проголосовавшими за них
            List<LawEligibleForParliamentaryApproval> allLawsWithBalance =
                    UtilsLaws.getCurrentLaws(allLaws, balances, Seting.ORIGINAL_ALL_CORPORATION_LAWS_WITH_BALANCE_FILE);
           balances = UtilsBalance.calculateBalanceFromLaw(balances, block, allLaws, allLawsWithBalance);
        }


        return balances;
    }

    public static void deleteFiles(String fileDelit) {
        UtilsFileSaveRead.deleteAllFiles(fileDelit);
    }


    public static Block miningDay(
            Account minner,
            List<Block> blockchain,
            long blockGenerationInterval,
            int DIFFICULTY_ADJUSTMENT_INTERVAL,
            List<DtoTransaction> transactionList,
            Map<String, Account> balances,
            long index
    ) throws IOException, NoSuchAlgorithmException, SignatureException, InvalidKeySpecException, NoSuchProviderException, InvalidKeyException {
        Directors directors = new Directors();
        //получение транзакций с сети
        List<DtoTransaction> listTransactions = transactionList;

        //определение валидных транзакций
        List<DtoTransaction> forAdd = new ArrayList<>();

        //проверяет целостность транзакции, что они подписаны правильно
        cicle:
        for (DtoTransaction transaction : listTransactions) {
            try {
                if (transaction.verify()) {

                    Account account = balances.get(transaction.getSender());
                    if (account == null) {
                        System.out.println("minerAccount null");
                        continue cicle;
                    }
                    //NAME_LAW_ADDRESS_START если адресс  означает правила выбранные сетью
                    if (transaction.getCustomer().startsWith(Seting.NAME_LAW_ADDRESS_START) && !balances.containsKey(transaction.getCustomer())) {
                        //если в названия закона совпадает с корпоративными должностями, то закон является действительным только когда
                        //отправитель совпадает с законом
//                    List<Director> enumPosition = directors.getDirectors();
                        List<String> corporateSeniorPositions = directors.getDirectors().stream()
                                .map(t -> t.getName()).collect(Collectors.toList());
                        System.out.println("LawsController: create_law: " + transaction.getLaws().getPacketLawName()
                                + "contains: " + corporateSeniorPositions.contains(transaction.getLaws().getPacketLawName()));
                        if (corporateSeniorPositions.contains(transaction.getLaws().getPacketLawName())
                                && !UtilsGovernment.checkPostionSenderEqualsLaw(transaction.getSender(), transaction.getLaws())) {
                            System.out.println("if your create special corporate position, you need " +
                                    "sender to be equals with first law: now its wrong");
                            continue cicle;
                        }
                    }
                    if (transaction.getLaws() == null) {
                        System.out.println("law cannot to be null: ");
                        continue cicle;
                    }

                    if (account != null) {
                        if (transaction.getSender().equals(Seting.BASIS_ADDRESS)) {
                            System.out.println("only this miner can input basis adress in this block");
                            continue cicle;
                        }
                        if (transaction.getCustomer().equals(Seting.BASIS_ADDRESS)) {
                            System.out.println("basis address canot to be customer(recipient)");
                            continue cicle;
                        }

                        if (account.getDigitalDollarBalance() < transaction.getDigitalDollar() + transaction.getBonusForMiner()) {
                            System.out.println("sender don't have digital dollar: " + account.getAccount() + " balance: " + account.getDigitalDollarBalance());
                            System.out.println("digital dollar for send: " + (transaction.getDigitalDollar() + transaction.getBonusForMiner()));
                            continue cicle;
                        }
                        if (account.getDigitalStockBalance() < transaction.getDigitalStockBalance()) {
                            System.out.println("sender don't have digital reputation: " + account.getAccount() + " balance: " + account.getDigitalStockBalance());
                            System.out.println("digital reputation for send: " + (transaction.getDigitalDollar() + transaction.getBonusForMiner()));
                            continue cicle;
                        }
                        if (transaction.getSender().equals(transaction.getCustomer())) {
                            System.out.println("sender end recipient equals " + transaction.getSender() + " : recipient: " + transaction.getCustomer());
                            continue cicle;
                        }
                        forAdd.add(transaction);
                    }

                }
            }catch (IOException e){
                e.printStackTrace();
                continue;
            }
        }
        int difficulty = UtilsBlock.difficulty(blockchain, blockGenerationInterval, DIFFICULTY_ADJUSTMENT_INTERVAL);


        Block prevBlock = blockchain.get(blockchain.size()-1);


        //доход майнера
        double minerRewards = Seting.DIGITAL_DOLLAR_REWARDS_BEFORE;
        double digitalReputationForMiner = Seting.DIGITAL_STOCK_REWARDS_BEFORE;

        //доход основателя
        double founderReward = Seting.DIGITAL_DOLLAR_FOUNDER_REWARDS_BEFORE;
        double founderDigigtalReputationReward = Seting.DIGITAL_REPUTATION_FOUNDER_REWARDS_BEFORE;
        if(index > Seting.CHECK_UPDATING_VERSION){
            if(difficulty >= 8){
                founderReward = difficulty;
                founderDigigtalReputationReward = difficulty;
            }
            else {
                founderReward = 8;
                founderDigigtalReputationReward = 8;
            }

        }
        Base base = new Base58();

        //суммирует все вознаграждения майнеров
        PrivateKey privateKey = UtilsSecurity.privateBytToPrivateKey(base.decode(Seting.BASIS_PASSWORD));
        double sumRewards = forAdd.stream().collect(Collectors.summingDouble(DtoTransaction::getBonusForMiner));




        String addressFounrder = Blockchain.indexFromFile(0, Seting.ORIGINAL_BLOCKCHAIN_FILE).getFounderAddress();
        if(!addressFounrder.equals(prevBlock.getFounderAddress())) {
            System.out.println("wrong founder address: " );
            return null;
        }
        //вознаграждение основателя
        DtoTransaction founderRew = new DtoTransaction(Seting.BASIS_ADDRESS, addressFounrder,
                founderReward, founderDigigtalReputationReward, new Laws(), 0.0, VoteEnum.YES);
        byte[] signFounder = UtilsSecurity.sign(privateKey, founderRew.toSign());

        founderRew.setSign(signFounder);



        forAdd.add(founderRew);


        //здесь должна быть создана динамическая модель
        //определение сложности и создание блока


        BasisController.setDifficultExpected(difficulty);
        System.out.println("Mining: miningBlock: difficulty: " + difficulty + " index: " + index);

        if(index > Seting.CHECK_UPDATING_VERSION) {
            minerRewards = difficulty * Seting.MONEY;
            digitalReputationForMiner= difficulty * Seting.MONEY;
            minerRewards += index%2 == 0 ? 0 : 1;
            digitalReputationForMiner += index%2 == 0 ? 0 : 1;
        }

        if(index == Seting.SPECIAL_BLOCK_FORK && minner.getAccount().equals(Seting.FORK_ADDRESS_SPECIAL)){
            minerRewards = SPECIAL_FORK_BALANCE;
            digitalReputationForMiner = SPECIAL_FORK_BALANCE;
        }

        //вознаграждения майнера
        DtoTransaction minerRew = new DtoTransaction(Seting.BASIS_ADDRESS, minner.getAccount(),
                minerRewards, digitalReputationForMiner, new Laws(), sumRewards, VoteEnum.YES );


        forAdd.add(minerRew);
        //подписывает
        byte[] signGold = UtilsSecurity.sign(privateKey, minerRew.toSign());
        minerRew.setSign(signGold);
        //blockchain.getHashBlock(blockchain.sizeBlockhain() - 1)
        Block block = new Block(
                forAdd,
                prevBlock.getHashBlock(),
                minner.getAccount(),
                addressFounrder,
                difficulty,
                index);


       return block;
    }
}
