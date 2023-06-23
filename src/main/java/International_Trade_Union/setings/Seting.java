package International_Trade_Union.setings;

import International_Trade_Union.about_usDraft.AboutUsDraft;
import International_Trade_Union.about_us_engDraft.AboutUsEngDraft;
import International_Trade_Union.CorporateCharterEngDraft.CorporateCharter;
import International_Trade_Union.governments.Directors;
import International_Trade_Union.governments.NamePOSITION;
import International_Trade_Union.utils.UtilsUse;

import java.util.List;
import java.util.Set;

public interface Seting {
    // значение используется для вычисления процентов
    int HUNDRED_PERCENT = 100;
    // значение используется как константа года,
    // в данной системе отсутствует високосный год
    int YEAR = 360;

    Directors directors = new Directors();


    //используется для очистки из файла, где хранятся отправленные транзакции,
    //чтобы предотвратить добавление повторно уже отправленных транзакций
    int DAY_DELETED_SENDED_FILE = 3;



    //За какой период последних блоков учитывать для отбора акционеров.
    //Акционерами могут быть только с наибольшим количеством баланса
    //отправители и майнеры.
    int BOARDS_BLOCK = (int) (Seting.COUNT_BLOCK_IN_DAY * YEAR);


    //минимальное значение количество положительных голосов, для того чтобы избрать
    // Совет Директоров и Совет Корпоративных Верховных Судей,
    int ORIGINAL_LIMIT_MIN_VOTE = 1; //(int) (200 * Seting.COUNT_BLOCK_IN_DAY * 1 / 8);


    //прямая демократия, сколько голосов нужно, чтобы правило вступило в силу,
    //без необходимости правительства
    double ALL_STOCK_VOTE = 1.0;



    //Минимальное значение чтобы Совет Корпоративных Верховных Судей могла избрать Верховного Судью
    int ORIGINAL_LIMIT_MIN_VOTE_CORPORATE_COUNCIL_OF_REFEREES = 2;

    //Минимальное значение остатка голосов чтобы Совет директоров утверждал бюджет,
    //стратегический план, в создании новых должностей и назначении новых должностей,
    //и т.д. Также участвовал в утверждении законов, вместе с другими участниками.
    int ORIGINAL_LIMIT_MIN_VOTE_BOARD_OF_DIRECTORS = 10;

    //Минимальное количество остатка голосов чтобы Совет Акционеров
    //утверждал вместе с остальными участниками в утверждении законов.
    int ORIGINAL_LIMIT_MIN_VOTE_BOARD_OF_SHAREHOLDERS = 10; //100;

    //голос Генерального Исполнительного Директора
    int ORIGINAL_LIMIT_MIN_VOTE_GENERAL_EXECUTIVE_DIRECTOR = 1;

    //фракционный голос минимум 10.0
    double ORIGINAL_LIMIT_MIN_VOTE_FRACTIONS = 10.0;

    //голос Верховного Судьи
    int ORIGINAL_LIMIT_MIN_VOTE_HIGHT_JUDGE = 1;

    //для преодоления верховного судьи, если Верховный Судья не одобрил закон
    //то нужно получить в два раза больше голосов, чтобы преодолеть вето Верховного Судьи
    int POWERFUL_VOTE = 2;

    //данная мера нужна чтобы если один счет голосует за несколько кандидатов,
    //его голос не делился равномерно, а становился значительно сильнее,
    //чтобы не допустить монополии, если очень богатый человек захочет должностные лица поставить к власти
    //то он не сможет пример: счет X проголосовал ЗА = 2 закона и ПРОТИВ = 3 закона
    //100 = voteYES, 100 = voteNO, voteYES / (2^3) = 12.5 , voteNO / (3^3) = 3.7
    //таким образом чем больше голосует, тем меньше голосов остается для избрания
    //должностных лиц, это защита от сверхбогатых участников Совета Акционеров
    int POWERING_FOR_VOTING = 3;


    //голос должностных лиц,
    int VOTE_GOVERNMENT = 1;
    //голос должностных лиц вместо акций учитывается только один
    //голос, как будто у них одна акция
    double STOCK_VOTE_GOVERNMENT = 1;

    //    процент который получает основатель от добычи
    Double FOUNDERS_REWARD = 2.0;

    //address for send rewards
    String BASIS_ADDRESS = "faErFrDnBhfSfNnj1hYjxydKNH28cRw1PBwDQEXH3QsJ";
    String BASIS_PASSWORD = "3hupFSQNWwiJuQNc68HiWzPgyNpQA2yy9iiwhytMS7rZyfPddNRwtvExeevhayzN6xL2YmTXN6NCA8jBhV9ge1w8KciHedGUMgZyq2T7rDdvekVNwEgf5pQrELv8VAEvQ4Kb5uviXJFuMyuD1kRAGExrZym5nppyibEVnTC9Uiw8YzUh2JmVT9iUajnVV3wJ5foMs";

    //сложность коррекция каждые n блоков
    int DIFFICULTY_ADJUSTMENT_INTERVAL = (int) (Seting.COUNT_BLOCK_IN_DAY / 2);
    int DIFFICULTY_ADJUSTMENT_INTERVAL_TEST = 10;

    long BLOCK_GENERATION_INTERVAL = Seting.BLOCK_TIME * 1000;// after Seting.BLOCK_TIME
    long BLOCK_GENERATION_INTERVAL_TEST = 0 * 1000;


    long INTERVAL_TARGET = 600000;
    long INTERVAL_TARGET_TEST = 25000;

    // плата за обслуживание каждые 6 месяцев.
    Double ANNUAL_MAINTENANCE_FREE_DIGITAL_DOLLAR_YEAR = 0.2;
    //отрицательная ставка для цифровой акции
    double ANNUAL_MAINTENANCE_FREE_DIGITAL_STOCK_YEAR = 0.4;
    //каждые сколько месяцев снимать
    int HALF_YEAR = 2;

    //стоимость создания закона 5
    double COST_LAW = 5;
    //с чего начинается адрес пакета закона
    //сокращенно корпорация
    String NAME_LAW_ADDRESS_START = "LIBER";

    int HASH_COMPLEXITY_GENESIS = 1;

    //совет акционеров
   int BOARD_OF_SHAREHOLDERS = 1500;

    //ПОПРАВКА В УСТАВЕ
   //требования к поправкам
    String AMENDMENT_TO_THE_CHARTER = "AMENDMENT_TO_THE_CHARTER";

    //директора созданные Советом директоров
    String ADD_DIRECTOR = "ADD_DIRECTOR_";



    //бюджет должен формировать только палата представителей
    String BUDGET = "BUDGET";

    //план также утверждается на четыре года и утверждается только палатой представителей
    //каждый план обязан содержать дату начала планирования с какого числа вступает в силу.
    //FOUR-YEAR PLAN
    String STRATEGIC_PLAN = "STRATEGIC_PLAN";


    //лимиты для ведения поправок
    //палата судей минимум 5 голосов
    int ORIGINAL_LIMIT_MIN_VOTE_CORPORATE_COUNCIL_OF_REFEREES_AMENDMENT = 5;// 5;
    //палата представителей 20% голосов
    int ORIGINAL_LIMIT_MIN_VOTE_BOARD_OF_DIRECTORS_AMENDMENT =
           directors.getDirector(NamePOSITION.BOARD_OF_DIRECTORS.toString()).getCount() * 20 / 100;

    //Совет акционеров минимум 20% голосов
    int ORIGINAL_LIMIT_MINT_VOTE_BOARD_OF_SHAREHOLDERS_AMENDMENT = BOARD_OF_SHAREHOLDERS * 20 / 100;




    //    адресс основателя: здесь будет мой адрес. Сейчас заглушка
    String ADDRESS_FOUNDER_TEST = "nNifuwmFZr7fnV1zvmpiyQDV5z7ETWvqR6GSeqeHTY43";
    String ADDRESS_FOUNDER = "nNifuwmFZr7fnV1zvmpiyQDV5z7ETWvqR6GSeqeHTY43";
    //начальная сумма основателя
    Double FOUNDERS_REMUNERATION_DIGITAL_DOLLAR = 65000000.0;
    double FOUNDERS_REMNUNERATION_DIGITAL_STOCK = 65000000.0;

    String CORPORATE_CHARTER_DRAFT = "";

    //КЛЮЧЕВОЕ НАЗВАНИЕ ПАКЕТА ЧТО ЭТО УСТАВ, ДЕЙСТВУЮЩИЙ УСТАВ ПОДПИСАН ОСНОВАТЕЛЕМ.
    String ORIGINAL_CHARTER_CURRENT_LAW_PACKAGE_NAME = "ORIGINAL_CHARTER_CURRENT_LAW_PACKAGE_NAME";

    //КЛЮЧЕВОЕ НАЗВАНИЕ ДЛЯ КОДА КОТОРЫЙ СОПРОВОЖДАЕТСЯ С УСТАВОМ
    String ORIGINAL_CHARTER_CURRENT_ALL_CODE = "ORIGINAL_CHARTER_CURRENT_ALL_CODE";




    // сколько секунд в сутках
    int DAY_SECOND = 86400;

    //    за сколько секунд добывается каждый блок
    int BLOCK_TIME = 150;


    //сколько блоков добывается в сутки
    double COUNT_BLOCK_IN_DAY = (DAY_SECOND / BLOCK_TIME);

    //подсчет голосов для должности в годах, учитываются только те голоса
    //которые не позже четырех лет для законов и должностей,
    //голоса отданные за законы должны обновляться каждые четыре года
    //как и за должности
    int POSITION_YEAR_VOTE = (int) Seting.COUNT_BLOCK_IN_DAY * YEAR * 4;
    //подсчет голосов для законов в годах
    int LAW_YEAR_VOTE = (int) Seting.COUNT_BLOCK_IN_DAY * YEAR * 4;

    String ORIGINAL_BLOCKCHAIN_FILE = "\\resources\\blockchain\\";
    String ORIGINAL_BALANCE_FILE = "\\resources\\balance\\";
    String ORIGINAL_BOARD_0F_SHAREHOLDERS_FILE = "\\resources\\federalGovernment\\federalGovernment.txt";
    String ORIGINAL_ALL_CORPORATION_LAWS_FILE = "\\resources\\federalLaws\\";
    String ORIGINAL_ACCOUNT = "\\resources\\minerAccount\\minerAccount.txt";
    String ORIGINAL_CORPORATE_VOTE_FILE = "\\resources\\vote\\";

    String ORIGINAL_ALL_CORPORATION_LAWS_WITH_BALANCE_FILE = "\\resources\\allLawsWithBalance\\";
    String ORGINAL_ALL_TRANSACTION_FILE = "\\resources\\transactions\\";
    String ORIGINAL_ALL_SENDED_TRANSACTION_FILE = "\\resources\\sendedTransaction\\";
    String ORIGINAL_POOL_URL_ADDRESS_FILE = "\\resources\\poolAddress\\";
    String ORIGINAL_POOL_URL_ADDRESS_BLOCKED_FILE = "\\resources\\pooAddressBlocked\\";

    String TEMPORARY_BLOCKCHAIN_FILE = "\\resources\\tempblockchain\\";


    //адресса внешних сервисов
    Set<String> ORIGINAL_ADDRESSES = Set.of("http://194.87.236.238:80");
    Set<String> ORIGINAL_BLOCKED_ADDRESS = Set.of("http://154.40.38.130:80",
            "http://10.0.36.2:80", "http://localhost:8083");

    int SIZE_FILE_LIMIT = 10;

    //папки файла для тестирования с сохранениям файла
    String TEST_LAST_BLOCK = ".\\src\\test\\java\\unitted_states_of_mankind\\resourceTestingFileSaved\\lastBlock\\";
    String INDEX_TEST = ".\\src\\test\\java\\unitted_states_of_mankind\\resourceTestingFileSaved\\index\\index.txt";
    String TEST_BLOCKCHAIN_SAVED = ".\\src\\test\\java\\unitted_states_of_mankind\\resourceTestingFileSaved\\network\\";
    String TEST_BLOCKCHAIN_BALANCES = ".\\src\\test\\java\\unitted_states_of_mankind\\resourceTestingFileSaved\\balances\\";
    String TEST_FEDERAL_GOVERNMENT = ".\\src\\test\\java\\unitted_states_of_mankind\\resourceTestingFileSaved\\federal government\\federalGovernment.txt";
    String TEST_FEDERAL_LAWS = ".\\src\\test\\java\\unitted_states_of_mankind\\resourceTestingFileSaved\\laws\\";
    String TEST_CURRENT_LAWS = ".\\src\\test\\java\\unitted_states_of_mankind\\resourceTestingFileSaved\\current laws\\";
    String TEST_FEDERAL_VOTE = ".\\src\\test\\java\\unitted_states_of_mankind\\resourceTestingFileSaved\\vote\\";
    String TEST_ALL_FEDERAL_LAWS_WITH_BALANCE_FILE = ".\\src\\test\\java\\unitted_states_of_mankind\\resourceTestingFileSaved\\allLawsWithBalance\\";


    //Временный блокчейн для тестирования
    String TEST_LAST_BLOCK_TEMPORARY = ".\\src\\test\\java\\unitted_states_of_mankind\\resourceTestingFileWithoutSave\\lastBlock\\";
    String TEST_INDEX_TEMPORARY = ".\\src\\test\\java\\unitted_states_of_mankind\\resourceTestingFileWithoutSave\\index\\index.txt";
    String TEST_TEMPORARY_BLOCKCHAIN = ".\\src\\test\\java\\unitted_states_of_mankind\\resourceTestingFileWithoutSave\\temporary blockchain\\";
    String TEST_BLOCKCHAIN_BALANCES_TEMPORARY = ".\\src\\test\\java\\unitted_states_of_mankind\\resourceTestingFileWithoutSave\\balances\\";
    String TEST_FEDERAL_GOVERNMENT_TEMPORARY = ".\\src\\test\\java\\unitted_states_of_mankind\\resourceTestingFileWithoutSave\\federal government\\federalGovernment.txt";
    String TEST_FEDERAL_LAWS_TEMPORARY = ".\\src\\test\\java\\unitted_states_of_mankind\\resourceTestingFileWithoutSave\\laws\\";
    String TEST_CURRENT_LAWS_TEMPORARY = ".\\src\\test\\java\\unitted_states_of_mankind\\resourceTestingFileWithoutSave\\current laws\\";
    String TEST_FEDERAL_VOTE_TEMPORARY = ".\\src\\test\\java\\unitted_states_of_mankind\\resourceTestingFileSaved\\vote\\";
    String TEST_ALL_FEDERAL_LAWS_WITH_BALANCE_TEMPORARY = ".\\unitedStates\\src\\test\\java\\unitted_states_of_mankind\\resourceTestingFileWithoutSave\\allLawsWithBalance\\";

    String TEST_FILE_WRITE_INFO = ".\\src\\test\\java\\unitted_states_of_mankind\\blockchainTwentyYearTest\\";
    double DIGITAL_DOLLAR_REWARDS_BEFORE = 400.0;
    double DIGITAL_STOCK_REWARDS_BEFORE = 400.0;
    double DIGITAL_DOLLAR_FOUNDER_REWARDS_BEFORE = Math.round(UtilsUse.countPercents(Seting.DIGITAL_DOLLAR_REWARDS_BEFORE, Seting.FOUNDERS_REWARD));
    double DIGITAL_REPUTATION_FOUNDER_REWARDS_BEFORE = Math.round(UtilsUse.countPercents(Seting.DIGITAL_STOCK_REWARDS_BEFORE, Seting.FOUNDERS_REWARD));


    List<String> firstTestingPeople = List.of(
            "25TzGfMpAygvuFvXujL4seof4LHpzC92crCTydGC9RmZP",
            "25YfXizU2SpF75tDoqyn11HWaXyq5tKWKk87NSwhbBW5C",
            "27MkHGZZnYkNtQMevRqBfAU2Pnu7LJEWC61AzMvAC31V3",
            "zA6pF1vGFqWNjnnP5XWcFodxfkZUX1VoyRgyZnaYrryo",
            "22a5XcurUDGGhJ3JncMnRS4Ka8LDRf7tpb6YJMjvTJFZr",
            "2BN56XDUzKW5NxWJvULr13ks78eJmXt59mtowomy6N9Ed",
            "s4rRNaA7HtoTtxCiseibvhpzoiqMozm9LrtWZ33jvi5q"
    );

    double digDollarRewTeam = 100000.0;
    double digStockRewTeam = 100000.0;

    //каким количеством порций отправлять блоки

    int PORTION_BLOCK_TO_SEND = 300;

}
