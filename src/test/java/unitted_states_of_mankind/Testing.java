package unitted_states_of_mankind;

import International_Trade_Union.config.BLockchainFactory;
import International_Trade_Union.config.BlockchainFactoryEnum;
import International_Trade_Union.controllers.BasisController;
import International_Trade_Union.entity.blockchain.Blockchain;
import International_Trade_Union.entity.blockchain.block.Block;
import International_Trade_Union.governments.Directors;
import International_Trade_Union.governments.NamePOSITION;
import International_Trade_Union.model.Mining;
import International_Trade_Union.setings.Seting;
import International_Trade_Union.utils.UtilUrl;
import International_Trade_Union.utils.UtilsBlock;
import org.json.JSONException;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@SpringBootTest
public class Testing {


    @Test
    public void generateOriginalBlocks() throws IOException, JSONException, InterruptedException {

        for (int i = 1; i < 2000; i++) {

            System.out.println("block generate i: " + i);
            try {
                UtilUrl.readJsonFromUrl("http://localhost:8082/mine");
            }catch (IOException e){
                System.out.println("error test mining");
                continue;
            }

        }

    }

    @Test
    public void testSorted(){
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
    public void testLimitedMoney(){
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
                if(block % (180 * 576) == 0){
                    digitalDollarAccount = digitalStockAccount - digitalDollarAccount * dollarPercent /100;
                    digitalStockAccount = digitalStockAccount - digitalStockAccount * stockPercent /100;
                }

                digitalDollarAccount += digitalDollarMining;
                digitalStockAccount += digitalStockMining;
            }
//            if(i%360 == 2){
//                digitalDollarMining = digitalDollarMining/2;
//                digitalStockAccount/= 2;
//            }
            if(digitalDollarAccount < 56000000000.0 && digitalDollarAccount > 5100000000.0){
                //при таких настройках, верхняя граница, должна быть достигнута к 334 году
                //денежная масса больше не будет выше пять миллиардов сто сорок миллионов
                System.out.println("block: " + block + " index: " + i + " year: " + (i%360));
                break;
            }
        }

        System.out.printf("digital dollar balance: %f\n", digitalDollarAccount);
        System.out.printf("digital stock balance: %f\n", digitalStockAccount);
    }

    @Test
    public void addblock() throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, SignatureException, NoSuchProviderException, InvalidKeyException {
        Blockchain blockchain = BLockchainFactory.getBlockchain(BlockchainFactoryEnum.ORIGINAL);
        blockchain = Mining.getBlockchain(

                Seting.ORIGINAL_BLOCKCHAIN_FILE,
                BlockchainFactoryEnum.ORIGINAL);

        BasisController.addBlock(blockchain.getBlockchainList());
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

        if(
                result > 10 || result < 0
        ){
            System.out.println("_____________________________________________");
            System.out.println("wrong timestamp:result " + result);
            ;

            System.out.println("_____________________________________________");

        }

        if(
                result2 > 10 || result2 < 0
        ){
            System.out.println("_____________________________________________");
            System.out.println("wrong timestamp:result2 " + result2);
            ;

            System.out.println("_____________________________________________");

        }
    }
}
