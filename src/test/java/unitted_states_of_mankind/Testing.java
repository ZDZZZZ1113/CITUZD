package unitted_states_of_mankind;

import International_Trade_Union.config.BLockchainFactory;
import International_Trade_Union.config.BlockchainFactoryEnum;
import International_Trade_Union.controllers.BasisController;
import International_Trade_Union.entity.DtoTransaction.DtoTransaction;
import International_Trade_Union.entity.blockchain.Blockchain;
import International_Trade_Union.entity.blockchain.block.Block;
import International_Trade_Union.entity.entities.EntityAccount;
import International_Trade_Union.entity.entities.EntityBlock;
import International_Trade_Union.entity.services.BlockService;
import International_Trade_Union.exception.NotValidTransactionException;
import International_Trade_Union.governments.Directors;
import International_Trade_Union.governments.NamePOSITION;
import International_Trade_Union.governments.UtilsGovernment;
import International_Trade_Union.model.Account;
import International_Trade_Union.model.Mining;
import International_Trade_Union.network.AllTransactions;
import International_Trade_Union.setings.Seting;
import International_Trade_Union.utils.*;
import International_Trade_Union.utils.base.Base;
import International_Trade_Union.utils.base.Base58;
import International_Trade_Union.vote.Laws;
import International_Trade_Union.vote.VoteEnum;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.spring5.processor.SpringTextareaFieldTagProcessor;

import javax.xml.bind.DatatypeConverter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import static International_Trade_Union.utils.BlockchainDifficulty.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class Testing {


    private static volatile boolean blockFound = false;
    private static volatile String foundHash = "-";

    @Test
    public void entityBalance() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, NoSuchProviderException, InvalidKeyException {
        Map<String, Account> balances = SaveBalances.readLineObject(Seting.ORIGINAL_BALANCE_FILE);
        List<Account> accountList = balances.entrySet().stream()
                .map(t->t.getValue())
                .collect(Collectors.toList());

        List<EntityAccount> entityAccounts = UtilsAccountToEntityAccount.accountsToEntityAccounts(balances);
        List<Account> testAccount = UtilsAccountToEntityAccount.EntityAccountToAccount(entityAccounts);
         assertEquals(testAccount, accountList);

    }
    @Test
    public void entityBlock() throws IOException {
        Block block = UtilsJson.jsonToBLock("{\"dtoTransactions\":[{\"sender\":\"faErFrDnBhfSfNnj1hYjxydKNH28cRw1PBwDQEXH3QsJ\",\"customer\":\"27MkHGZZnYkNtQMevRqBfAU2Pnu7LJEWC61AzMvAC31V3\",\"digitalDollar\":400.0,\"digitalStockBalance\":400.0,\"laws\":{\"packetLawName\":null,\"laws\":null,\"hashLaw\":null},\"bonusForMiner\":0.0,\"voteEnum\":\"YES\",\"sign\":\"MEUCIEHrC1uypFUgsXM/Z6yN/AM1qb+Q545RiU5FGFoFVvnGAiEA/adtMyE6ffnsOEVlXl+rbx2NstboFVEoY4D0EZuXfow=\"},{\"sender\":\"faErFrDnBhfSfNnj1hYjxydKNH28cRw1PBwDQEXH3QsJ\",\"customer\":\"nNifuwmFZr7fnV1zvmpiyQDV5z7ETWvqR6GSeqeHTY43\",\"digitalDollar\":8.0,\"digitalStockBalance\":8.0,\"laws\":{\"packetLawName\":null,\"laws\":null,\"hashLaw\":null},\"bonusForMiner\":0.0,\"voteEnum\":\"YES\",\"sign\":\"MEQCIDXMAVvYsJLJLDVGm0bIfJhd58Jzv2OKrAlWVWH6mWlOAiACDeYNeQlkupje6M53315qV2W5VVHLLW+6nvh3zi32sw==\"}],\"previousHash\":\"00da347d3b3d4c9fc5c826fcff7b06569b673c63fc1afdaf7b9075b175f772be\",\"minerAddress\":\"27MkHGZZnYkNtQMevRqBfAU2Pnu7LJEWC61AzMvAC31V3\",\"founderAddress\":\"nNifuwmFZr7fnV1zvmpiyQDV5z7ETWvqR6GSeqeHTY43\",\"randomNumberProof\":12,\"minerRewards\":0.0,\"hashCompexity\":1,\"timestamp\":1685946378876,\"index\":20,\"hashBlock\":\"039bf7b8b0be69125b93b26018df6b342d7c64305b6405cfa3b4813dae2bc682\"}");
        EntityBlock entityBlock = UtilsBlockToEntityBlock.blockToEntityBlock(block);
        System.out.println("***************************************");

        System.out.println(block);
        System.out.println("***************************************");

        Block testBlock = UtilsBlockToEntityBlock.entityBlockToBlock(entityBlock);
        System.out.println(testBlock);

        System.out.println("getHashCompexity: " + (block.getHashCompexity() == testBlock.getHashCompexity()));
        System.out.println("getHashBlock: " + (block.getHashBlock().equals(testBlock.getHashBlock())));
        System.out.println("getMinerRewards: " + (block.getMinerRewards() == testBlock.getMinerRewards()));
        System.out.println("getFounderAddress: " + (block.getFounderAddress().equals(testBlock.getFounderAddress())));
        System.out.println("getMinerAddress: " + (block.getMinerAddress().equals(testBlock.getMinerAddress())));
        System.out.println("getPreviousHash: " + (block.getPreviousHash().equals(testBlock.getPreviousHash())));
        System.out.println("getIndex: " + (block.getIndex() == testBlock.getIndex()));
        System.out.println("getTimestamp: " + (block.getTimestamp().equals(testBlock.getTimestamp())));
        System.out.println("getRandomNumberProof: " + (block.getRandomNumberProof() == testBlock.getRandomNumberProof()));
        for (int i = 0; i < testBlock.getDtoTransactions().size(); i++) {
            DtoTransaction transaction = block.getDtoTransactions().get(i);
            DtoTransaction transaction1 = testBlock.getDtoTransactions().get(i);
            System.out.println("getCustomer: " + (transaction.getCustomer().equals(transaction1.getCustomer())));
            System.out.println("getSender: " + (transaction.getSender().equals(transaction1.getSender())));
            System.out.println("getBonusForMiner: " + (transaction.getBonusForMiner() == transaction1.getBonusForMiner()));
            System.out.println("getDigitalDollar: " + (transaction.getDigitalDollar() == transaction1.getDigitalDollar()));
            System.out.println("getDigitalStockBalance: " + (transaction.getDigitalStockBalance() == transaction1.getDigitalStockBalance()));
            System.out.println("getVoteEnum: " + (transaction.getVoteEnum().equals(transaction1.getVoteEnum())));
            System.out.println("getSign: " + (transaction.getSign().equals(transaction1.getSign())));

            System.out.println("transaction.getLaws(): " + transaction.getLaws());
            System.out.println("transaction1.getLaws(): " + transaction1.getLaws());

            if(transaction.getLaws().getHashLaw() == null && transaction1.getLaws().getHashLaw()== null){
                System.out.println("getHashLaw true");
            }
            if(transaction.getLaws().getLaws() == null && transaction1.getLaws().getLaws()== null){
                System.out.println("getLaws true");
            }


        }

//        testBlock.getDtoTransactions().get(0).getLaws().setPacketLawName("32");
        assertEquals(testBlock, block);


    }

    @Test
    public void multipleFindHash() throws IOException, NoSuchAlgorithmException, SignatureException, InvalidKeySpecException, NoSuchProviderException, InvalidKeyException {
        System.out.println("find hash method");
        int randomNumberProofStatic = 0;
        int differrentNumber = 0;
        int INCREMENT_VALUE = 100000;
        int hashCoplexity = 2;
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            System.out.println(":i: " + i);
            int finalDifferrentNumber = differrentNumber;
            Thread thread = new Thread(() -> {
                long nonce = randomNumberProofStatic + finalDifferrentNumber;
                String tempHash = "";
                int size = UtilsStorage.getSize();
                Timestamp previus = new Timestamp(UtilsTime.getUniversalTimestamp());
                String nameThread = Thread.currentThread().getName();
                while (!blockFound) {


//                    System.out.printf("\tTrying %d to find a block: ThreadName %s:\n ", nonce , nameThread);
                    Instant instant1 = Instant.ofEpochMilli(UtilsTime.getUniversalTimestamp());
                    Instant instant2 = previus.toInstant();

                    Duration duration = Duration.between(instant1, instant2);
                    long seconds = duration.getSeconds();

                    tempHash = UtilsUse.sha256hash("hello: " + nonce);


                    if (seconds > 10 || seconds < -10) {
                        long milliseconds = instant1.toEpochMilli();
                        previus = new Timestamp(milliseconds);
                        previus.setTime(milliseconds);

                        //проверяет устаревание майнинга, если устарел - прекращает майнинг

//                        int tempSize = UtilsStorage.getSize();
//                        if (size < tempSize) {
//                            Mining.miningIsObsolete = true;
//                            System.out.println("someone mined a block before you, the search for this block is no longer relevant and outdated: " + tempHash);
//
//                            synchronized (Block.class) {
//                                if (!blockFound) {
//                                    blockFound = true;
//                                    Mining.miningIsObsolete = true;
//                                    foundHash = tempHash;
//                                }
//                            }
//                            System.out.println("Block found: hash: " + tempHash);
//                            break;
//
//                        }

                    }

                    //если true, то прекращаем майнинг
//                    if (Mining.isIsMiningStop()) {
//                        System.out.println("mining will be stopped");
//
//                        synchronized (Block.class) {
//                            if (!blockFound) {
//                                blockFound = true;
//                                Mining.miningIsObsolete = true;
//                                foundHash = tempHash;
//                            }
//                        }
//                        System.out.println("Block found: hash: " + tempHash);
//
//                        foundHash= tempHash;
//                        break;
//
//                    }
//

                    //если true, то прекращаем майнинг. Правильный блок найден
                    if (UtilsUse.chooseComplexity(tempHash, hashCoplexity, 30001)) {
                        System.out.println("block found: hash: " + tempHash);
                        synchronized (Testing.class) {
                            if (!blockFound) {
                                blockFound = true;
//                                Mining.miningIsObsolete = true;
                                foundHash = tempHash;
                            }
                        }
                        System.out.println("Block found: hash: " + tempHash);
                        foundHash = tempHash;
                        break;
                    }
                    nonce++;
                }

            });

            differrentNumber += INCREMENT_VALUE;
            threads.add(thread);
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("foundhash: " + foundHash);
    }

    @Test
    public void actualTransactionsInServer() throws InterruptedException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, NoSuchProviderException, InvalidKeyException {
        List<DtoTransaction> temporaryDtoList = AllTransactions.getInstance();
        System.out.println("size: " + temporaryDtoList.size());
        for (DtoTransaction transaction : temporaryDtoList) {
            System.out.println(transaction);
        }

    }

    Map<String, Account> cheater = new HashMap<>();

    @GetMapping("/showCheater")
    public void showCheater() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, NoSuchProviderException, InvalidKeyException {
        Blockchain blockchain = Mining.getBlockchain(
                Seting.ORIGINAL_BLOCKCHAIN_FILE,
                BlockchainFactoryEnum.ORIGINAL);

        List<Block> blocks = blockchain.getBlockchainList();
        for (Block block : blocks) {
            if (block.getIndex() > Seting.NEW_CHECK_UTILS_BLOCK &&
                    !block.getHashBlock().equals(block.hashForTransaction())) {
                System.out.println("false hash added wrong hash");
//            System.out.println("actual: " + thisBlock.getHashBlock());
//            System.out.println("expected: " + thisBlock.hashForTransaction());
                System.out.println("address: " + block.getMinerAddress());


                //for find cheater
                stop:
                for (DtoTransaction dtoTransaction : block.getDtoTransactions()) {
                    if (dtoTransaction.getSender().equals(Seting.BASIS_ADDRESS) &&
                            dtoTransaction.getCustomer().equals(block.getMinerAddress())) {
                        String address = block.getMinerAddress();
                        double dollar = dtoTransaction.getDigitalDollar();
                        double stock = dtoTransaction.getDigitalStockBalance();
                        System.out.printf("cheater address %s: stole dollar %f end stock %f: from block index %d ",
                                address, dollar, stock, block.getIndex());


                        if (cheater.containsKey(address)) {
                            double sumDollar = cheater.get(address).getDigitalDollarBalance() + dollar;
                            double sumStock = cheater.get(address).getDigitalStockBalance() + stock;
                            Account account = new Account(address, sumDollar, sumStock);
                            cheater.put(address, account);
                        } else {
                            Account account = new Account(address, dollar, stock);
                            cheater.put(address, account);
                        }
                        break stop;
                    }
                }


            }


        }

    }

    @Test
    public void generateOriginalBlocks() throws IOException, JSONException, InterruptedException {

        for (int i = 1; i < 2000; i++) {

            System.out.println("block generate i: " + i);
            try {
                UtilUrl.readJsonFromUrl("http://localhost:8082/mine");
            } catch (IOException e) {
                System.out.println("error test mining");
                continue;
            }

        }

    }

    @Test
    public void testSorted() {
        // Изначальное значение хэш рейта
        long hashRate = 1; // Начинаем с 1 H/s

        // Вычисление SHA-256 хешей
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");

            // Пример вычисления хеша строки "Hello, World!"
            String input = "Hello, World!";
            byte[] inputBytes = input.getBytes(StandardCharsets.UTF_8);

            long startTime = System.currentTimeMillis();
            long elapsedTime = 0;

            // Увеличиваем хэш рейт до тех пор, пока время выполнения цикла не превысит одну секунду
            while (elapsedTime < 1000) {
                long numberOfHashes = hashRate;

                for (long i = 0; i < numberOfHashes; i++) {
                    sha256.digest(inputBytes);
                }

                long endTime = System.currentTimeMillis();
                elapsedTime = endTime - startTime;

                // Увеличиваем хэш рейт в 10 раз для следующей итерации
                hashRate *= 2;
            }

            System.out.println("Количество хешей SHA-256, которые может перебирать один поток процессора в одну секунду: " + (hashRate / 10));
            System.out.println("Время, затраченное на вычисление хешей: " + elapsedTime + " миллисекунд");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sendBlocks() throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, SignatureException, NoSuchProviderException, InvalidKeyException {
        System.out.println(":CONFLICT TREE, IN GLOBAL DIFFERENT TREE: " + HttpStatus.CONFLICT.value());
        System.out.println(":GOOD SUCCESS: " + HttpStatus.OK.value());
        System.out.println(":FAIL BAD BLOCKHAIN: " + HttpStatus.EXPECTATION_FAILED.value());
        System.out.println(":CONFLICT VERSION: " + HttpStatus.FAILED_DEPENDENCY.value());
        System.out.println(":NAME CONFLICT: " + HttpStatus.NOT_ACCEPTABLE.value());
        System.out.println("two miner addresses cannot be consecutive: " + HttpStatus.NOT_ACCEPTABLE.value());
        System.out.println("PARITY ERROR" + HttpStatus.LOCKED);
        System.out.println("Test version: If the index is even, then the stock balance must also be even; if the index is not even, all can mining"
                + HttpStatus.LOCKED.value());


    }

    @Test
    public void testLimitedMoney() {
        int block = 0;
        double digitalDollarMining = 400;
        double digitalStockMining = 400;
        double dollarPercent = 0.2;
        double stockPercent = 0.4;
        double digitalDollarAccount = 0;
        double digitalStockAccount = 0;
        int year = 360 * 600;

        for (int i = 0; i < year; i++) {
            for (int j = 0; j < 576; j++) {
                block++;
                if (block % (180 * 576) == 0) {
                    digitalDollarAccount = digitalStockAccount - digitalDollarAccount * dollarPercent / 100;
                    digitalStockAccount = digitalStockAccount - digitalStockAccount * stockPercent / 100;
                }

                digitalDollarAccount += digitalDollarMining;
                digitalStockAccount += digitalStockMining;
            }
//            if(i%360 == 2){
//                digitalDollarMining = digitalDollarMining/2;
//                digitalStockAccount/= 2;
//            }
            if (digitalDollarAccount < 56000000000.0 && digitalDollarAccount > 5100000000.0) {
                //при таких настройках, верхняя граница, должна быть достигнута к 334 году
                //денежная масса больше не будет выше пять миллиардов сто сорок миллионов
                System.out.println("block: " + block + " index: " + i + " year: " + (i % 360));
                break;
            }
        }

        System.out.printf("digital dollar balance: %f\n", digitalDollarAccount);
        System.out.printf("digital stock balance: %f\n", digitalStockAccount);
    }


    @Test
    public void testHashDifficulty() throws IOException, NoSuchAlgorithmException, SignatureException, InvalidKeySpecException, NoSuchProviderException, InvalidKeyException {
        String hash = "8c41b1ddc22ba3402ed0489952ba45161b279abb3b3b8ebc6bede062a21f83ff";
//        hash = bytesToBinary(hash.getBytes());
//        int count = countLeadingZeros(hash);
//       printBitSet(hash.getBytes());
//       BlockchainDifficulty.printBinary(hash.getBytes());
//        System.out.println(count);
        System.out.println("**********************************************");
        String hello = "папрарпарыек546нгу авпвкпкккккккккккккккккккккуке";
        int nonce = 0;
        String testHash = "";
        Timestamp actualTime = new Timestamp(UtilsTime.getUniversalTimestamp());


        int diffInStr = 8;
        int difficulty = 2;
        while (true) {

            testHash = UtilsUse.sha256hash(hello + nonce);


            hash = testHash;
//            hash = bytesToBinary(testHash.getBytes());

//            String binary = bytesToBinary(hash.getBytes());
//            int leadingZeros = countLeadingZeros(binary);
            int leadingZeros = countLeadingZeroBits(hash.getBytes());
            boolean countInHash = UtilsUse.hashComplexity(hash, diffInStr);
//            boolean countInHash = true;
            boolean test = leadingZeros >= difficulty && countInHash;
//            boolean test = countInHash;

            if (test) {

                break;
            }
            nonce++;

        }
        Timestamp last = new Timestamp(UtilsTime.getUniversalTimestamp());
        Long result = actualTime.toInstant().until(last.toInstant(), ChronoUnit.SECONDS);
        System.out.println("different time: " + result);
        System.out.println("***********************************");
//        printBitSet(testHash.getBytes());

        System.out.println("***********************************");
        BlockchainDifficulty.printBinary(hash.getBytes());
        System.out.println("hash: " + hash);
    }

    @Test
    public void Account() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, NoSuchProviderException, InvalidKeyException {
        Map<String, Account> originals = SaveBalances.readLineObject("C://strategy1/balance/");
        Account account = originals.get("hzhq1LUk3qCcNyrTGE5pSRrRsYf3HkdSmeu5jap1JUnx");
        System.out.println(account);
    }

    @Test
    public void testHash() throws IOException {
        String json = "{\"dtoTransactions\":[],\"previousHash\":\"previous\",\"minerAddress\":\"mineAdres\",\"founderAddress\":\"founder\",\"randomNumberProof\":1,\"minerRewards\":0.0,\"hashCompexity\":2,\"timestamp\":1694088872091,\"index\":53392,\"hashBlock\":\"8c59604158f2f4ce093e7bef8ae46fc471071d95f24221e85b8b589dcff32a13\"}";
        String wrong = "{\"dtoTransactions\":[{\"sender\":\"faErFrDnBhfSfNnj1hYjxydKNH28cRw1PBwDQEXH3QsJ\",\"customer\":\"beACedNewaJU6BMFN8CrMeoRNArUHYEYTqZjQJCmNmRC\",\"digitalDollar\":400.0,\"digitalStockBalance\":400.0,\"laws\":{\"packetLawName\":null,\"laws\":null,\"hashLaw\":null},\"bonusForMiner\":0.0,\"voteEnum\":\"YES\",\"sign\":\"MEUCIHJezsfngLx2etBXHGA0eTMZOHZ0RGDL32HyJ4Qiv+ArAiEA2LellhnxyDwHUZ/8hz5KgEexhsWRg32k/Q2cd5UJbGo=\"},{\"sender\":\"faErFrDnBhfSfNnj1hYjxydKNH28cRw1PBwDQEXH3QsJ\",\"customer\":\"nNifuwmFZr7fnV1zvmpiyQDV5z7ETWvqR6GSeqeHTY43\",\"digitalDollar\":8.0,\"digitalStockBalance\":8.0,\"laws\":{\"packetLawName\":null,\"laws\":null,\"hashLaw\":null},\"bonusForMiner\":0.0,\"voteEnum\":\"YES\",\"sign\":\"MEUCIQCUL3TvVodxb6aHhtfHEPcbxps20nqylU0ksaLq1XJb8AIgMnfMkbAPH8tUpw9LML5JtwLY0o1bXqr3JAuHQazGeB0=\"}],\"previousHash\":\"000006e63b836e089c34bf8d7cb4bf024feb67b3c70e03e6f24bc2fc0b6637d0\",\"minerAddress\":\"beACedNewaJU6BMFN8CrMeoRNArUHYEYTqZjQJCmNmRC\",\"founderAddress\":\"nNifuwmFZr7fnV1zvmpiyQDV5z7ETWvqR6GSeqeHTY43\",\"randomNumberProof\":0,\"minerRewards\":0.0,\"hashCompexity\":5,\"timestamp\":1690488334985,\"index\":24281,\"hashBlock\":\"000000b0bef5b152f5ac9d68a9185e94de3e6a7368e4a2cb7b5e4d4fe2c36208\"}";
        Block block = UtilsJson.jsonToBLock(json);
        Block wrongHash = UtilsJson.jsonToBLock(wrong);
        assertEquals(block.getHashBlock(), block.hashForTransaction());
        assertNotEquals(wrongHash.getHashBlock(), wrongHash.hashForTransaction());
    }

    public String send(@RequestParam String sender,
                       @RequestParam String recipient,
                       @RequestParam Double dollar,
                       @RequestParam Double stock,
                       @RequestParam Double reward,
                       @RequestParam String password) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException, IOException, SignatureException, InvalidKeyException {
        Base base = new Base58();
        String result = "wrong";
        if (dollar == null)
            dollar = 0.0;

        if (stock == null)
            stock = 0.0;

        if (reward == null)
            reward = 0.0;

        Laws laws = new Laws();
        laws.setLaws(new ArrayList<>());
        laws.setHashLaw("");
        laws.setPacketLawName("");
        DtoTransaction dtoTransaction = new DtoTransaction(
                sender,
                recipient,
                dollar,
                stock,
                laws,
                reward,
                VoteEnum.YES);
        PrivateKey privateKey = UtilsSecurity.privateBytToPrivateKey(base.decode(password));
        byte[] sign = UtilsSecurity.sign(privateKey, dtoTransaction.toSign());
        System.out.println("Main Controller: new transaction: vote: " + VoteEnum.YES);
        dtoTransaction.setSign(sign);
        Directors directors = new Directors();
        System.out.println("sender: " + sender);
        System.out.println("recipient: " + recipient);
        System.out.println("dollar: " + dollar + ": class: " + dollar.getClass());
        System.out.println("stock: " + stock + ": class: " + stock.getClass());
        System.out.println("reward: " + reward + ": class: " + reward.getClass());
        System.out.println("password: " + password);
        System.out.println("sign: " + dtoTransaction.toSign());
        System.out.println("verify: " + dtoTransaction.verify());

        if (dtoTransaction.verify()) {

            //если в названия закона совпадает с корпоративными должностями, то закон является действительным только когда
            //отправитель совпадает с законом
            List<String> corporateSeniorPositions = directors.getDirectors().stream()
                    .map(t -> t.getName()).collect(Collectors.toList());
            System.out.println("LawsController: create_law: " + laws.getPacketLawName() + "contains: " + corporateSeniorPositions.contains(laws.getPacketLawName()));
            if (corporateSeniorPositions.contains(laws.getPacketLawName()) && !UtilsGovernment.checkPostionSenderEqualsLaw(sender, laws)) {
                System.out.println("sending" + "wrong transaction: Position to be equals whith send");
                return result;
            }
            result = dtoTransaction.toSign();

            String str = base.encode(dtoTransaction.getSign());
            System.out.println("sign: " + str);
            AllTransactions.addTransaction(dtoTransaction);
            String jsonDto = UtilsJson.objToStringJson(dtoTransaction);
            for (String s : Seting.ORIGINAL_ADDRESSES) {

                String original = s;
                String url = s + "/addTransaction";
                //если адресс совпадает с внутреним хостом, то не отправляет самому себе
                if (BasisController.getExcludedAddresses().contains(url)) {
                    System.out.println("MainController: its your address or excluded address: " + url);
                    continue;
                }
                try {
                    //отправка в сеть
                    UtilUrl.sendPost(jsonDto, url);

                } catch (Exception e) {
                    System.out.println("exception discover: " + original);

                }
            }


        } else
            return result;
        return result;

    }

    @Test
    public void testBalance() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, NoSuchProviderException, InvalidKeyException {
        Map<String, Account> originals = SaveBalances.readLineObject("C://strategy1/balance/");
        Map<String, Account> forks = SaveBalances.readLineObject("C://strategy2/balance/");
        Map<String, Account> balance = SaveBalances.readLineObject("C://resources/balance/");
        Map<String, Account> differents = new HashMap<>();


        Map<String, Account> cheaterAccount = new HashMap<>();
        Map<String, Integer> countAddressCheater = new HashMap<>();
        BufferedReader reader = null;
        try {
            // Открываем файл для чтения
            reader = new BufferedReader(new FileReader("C://cheater miner/1.txt"));

            String line;
            // Читаем файл построчно, пока не достигнем конца файла (null)
            while ((line = reader.readLine()) != null) {
                // Обрабатываем текущую строку
                String[] arr = line.split(":");
                if (cheaterAccount.containsKey(arr[0])) {
                    double dollar = Double.valueOf(arr[1]) + cheaterAccount.get(arr[0]).getDigitalDollarBalance();
                    double stock = Double.valueOf(arr[2]) + cheaterAccount.get(arr[0]).getDigitalStockBalance();
                    ;
                    Account account = new Account(arr[0], dollar, stock);
                    cheaterAccount.put(arr[0], account);
                } else {
                    String address = arr[0];

                    try {
                        double dollar = Double.valueOf(arr[1]);
                        double stock = Double.valueOf(arr[2]);
//                        System.out.println("dollar: " + dollar + " stock: " + stock + " index: " + arr[3]);
                        Account account = new Account(address, dollar, stock);
                        cheaterAccount.put(address, account);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        continue;
                    } catch (NumberFormatException e) {
                        continue;
                    }
                }

                if (countAddressCheater.containsKey(arr[0])) {
                    int count = countAddressCheater.get(arr[0]) + 1;
                    countAddressCheater.put(arr[0], count);
                } else {
                    countAddressCheater.put(arr[0], 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // Закрываем BufferedReader после использования
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        for (Map.Entry<String, Account> cheater : cheaterAccount.entrySet()) {
            System.out.println("cheater: " + cheater);
        }
        for (Map.Entry<String, Integer> countAccount : countAddressCheater.entrySet()) {
            System.out.println("chear how much: " + countAccount);
        }


        //----------------------------------------------------------------------
        for (Map.Entry<String, Account> original : originals.entrySet()) {
            String address = original.getKey();
            if (forks.containsKey(address)) {
                double originalDollar = original.getValue().getDigitalDollarBalance();
                double originalStock = original.getValue().getDigitalStockBalance();
                double forkDollar = forks.get(address).getDigitalDollarBalance();
                double forkStock = forks.get(address).getDigitalStockBalance();
                double resultDollar = originalDollar - forkDollar;
                double resultStock = originalStock - forkStock;
                if (resultDollar < 0) {
                    resultDollar = 0;
                    System.out.println("dollar original: " + originalDollar + " fork: " + forkDollar);
                }


                if (resultStock < 0) {
                    resultStock = 0;
                    System.out.println("stock original: " + originalStock + " fork: " + forkStock);
                }

                Account account = new Account(address, resultDollar, resultStock);
                differents.put(address, account);
            } else {
                double originalDollar = original.getValue().getDigitalDollarBalance();
                double originalStock = original.getValue().getDigitalStockBalance();
                Account account = new Account(address, originalDollar, originalStock);
                differents.put(address, account);
            }
        }

        HashMap<String, Account> afterFork = new HashMap<>();
        for (Map.Entry<String, Account> different : differents.entrySet()) {
            String address = different.getKey();
            if (forks.containsKey(address)) {
                double originalDollar = different.getValue().getDigitalDollarBalance();
                double originalStock = different.getValue().getDigitalStockBalance();
                double forkDollar = forks.get(address).getDigitalDollarBalance();
                double forkStock = forks.get(address).getDigitalStockBalance();

                double resultDollar = forkDollar + originalDollar;
                double resultStock = forkStock + originalStock;
                Account account = new Account(address, resultDollar, resultStock);
                afterFork.put(address, account);
            } else {
                double originalDollar = different.getValue().getDigitalDollarBalance();
                double originalStock = different.getValue().getDigitalStockBalance();
                Account account = new Account(address, originalDollar, originalStock);
                afterFork.put(address, account);
            }
        }
        for (Map.Entry<String, Account> different : differents.entrySet()) {
            String address = different.getKey();
            if (cheaterAccount.containsKey(address)) {
                double dollar = different.getValue().getDigitalDollarBalance();
                double stock = different.getValue().getDigitalStockBalance();
                dollar = dollar - cheaterAccount.get(address).getDigitalDollarBalance();
                System.out.println("====================");
                System.out.println("cheter: " + cheaterAccount.get(address).getDigitalDollarBalance());
                stock = stock - cheaterAccount.get(address).getDigitalStockBalance();
                if (dollar < 0)
                    dollar = 0;
                if (stock < 0)
                    stock = 0;

//                different.getValue().setDigitalStockBalance(dollar);
//                different.getValue().setDigitalStockBalance(stock);
                differents.get(address).setDigitalDollarBalance(dollar);
                differents.get(address).setDigitalDollarBalance(stock);
            }
        }


        List<Account> originalAccounts = originals.entrySet().stream()
                .map(t -> t.getValue()).collect(Collectors.toList());
        List<Account> afterForkAccounts = afterFork.entrySet().stream()
                .map(t -> t.getValue())
                .collect(Collectors.toList());
        List<Account> differentAcount = differents.entrySet().stream()
                .map(t -> t.getValue()).collect(Collectors.toList());


        double originalSumDollar = originalAccounts.stream().mapToDouble(t -> t.getDigitalDollarBalance()).sum();
        double originalSumStock = originalAccounts.stream().mapToDouble(t -> t.getDigitalStockBalance()).sum();
        double afterForkSumDollar = afterForkAccounts.stream().mapToDouble(t -> t.getDigitalDollarBalance()).sum();
        double afterForkSumsStock = afterForkAccounts.stream().mapToDouble(t -> t.getDigitalStockBalance()).sum();

        List<Account> forkAccount = forks.entrySet().stream().map(t -> t.getValue())
                .collect(Collectors.toList());

        double forkSumDollar = forkAccount.stream().mapToDouble(t -> t.getDigitalDollarBalance()).sum();
        double forkSumStock = forkAccount.stream().mapToDouble(t -> t.getDigitalStockBalance()).sum();

        System.out.printf("dollar: original: %f after fork: %f: \n", originalSumDollar, afterForkSumDollar);
        System.out.printf("stock: original: %f after fork: %f: \n", originalSumStock, afterForkSumsStock);
        System.out.printf(" different dollar: %f: \n", (originalSumDollar - afterForkSumDollar));
        System.out.printf(" different stock: %f: \n", (originalSumStock - afterForkSumsStock));
        System.out.printf("different fork dollar: %f: \n ", (originalSumDollar - forkSumDollar));
        System.out.printf("different fork stock: %f: \n ", (originalSumStock - forkSumStock));
        System.out.printf("dollar: %f: stock %f: \n ", forkSumDollar, forkSumStock);

        String address = "tjghGks15LdppYYvZKwb79w6wU2NwgpEeq5Rktj7smHH";
        System.out.println("****************************");
        System.out.println(address + ":" + cheaterAccount.containsKey(address));
        System.out.println("different balance: " + differents.get(address));
        System.out.println("orginal: " + originals.get(address));
        System.out.println("balance: " + balance.get(address));
        if (forks != null) {
            System.out.println("fork: " + forks.get(address));
            System.out.println("chetaer Account: " + cheaterAccount.get(address));
//        Account fork = forks.get(address);
//        Account different = differents.get(address);
//        double dollar = fork.getDigitalDollarBalance() - different.getDigitalDollarBalance();
//        double stock = fork.getDigitalStockBalance() - different.getDigitalStockBalance();
//        System.out.println("dollar: " + dollar);
//        System.out.println("stock: " + stock);
        }

    }

    void printBitSet(byte[] bytes) {
        BitSet bits = BitSet.valueOf(bytes);

        for (int i = 0; i < bits.length(); i++) {
            if (bits.get(i)) {
                System.out.print(1);
            } else {
                System.out.print(0);
            }
        }

        System.out.println();
    }

    @Test
    public void TestChangeDiff() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, NoSuchProviderException, InvalidKeyException, InterruptedException {
        Timestamp first = Timestamp.from(Instant.now());
        Thread.sleep(60000);
        Timestamp second = Timestamp.from(Instant.now());
        long result = second.toInstant().until(first.toInstant(), ChronoUnit.MINUTES);
        Long result2 = first.toInstant().until(second.toInstant(), ChronoUnit.MINUTES);
        System.out.println("result1 " + result);
        System.out.println("result2: " + result2);

    }
}
