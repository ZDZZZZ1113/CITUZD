package International_Trade_Union.originalCorporateCharter;

public interface OriginalCHARTER {
    String POWERS_OF_THE_BOARD_OF_DIRECTORS = " Полномочия Совета Директоров. " +
            " Совет Директоров может утверждать счета которые подали на должности из списка Directors. " +
            " Также Законы название пакетов начинаются с ADD_DIRECTOR являются пакетами которые содержать " +
            " список новых директоров которые должны управлять новыми линейками продукции. Данные " +
            " Законы может утверждать только Совет директоров и оттуда будет взять список законов, где " +
            " каждая строка которая начинается ADD_DIRECTOR будет добавлена в список Directors в качестве новой " +
            " должности на которую можно подать. " +
            " пакет который начинается  BUDGET является бюджетом и может его утвердить только Совет директоров. " +
            " Действующий бюджет может быть только один. " +
            " Совет директоров также утверждает стратегический план STRATEGIC_PLAN. Действующим может быть только " +
            " один стратегический план. " +
            " Совет директоров также участвует в утверждении законов (правил по которым должны действовать " +
            " все участники корпорации), а также участвует в утверждении внедрения поправок в устав AMENDMENT_TO_THE_CHARTER." +
            "" +
            " Совет имеет право устанавливать и собирать комиссию от продаж внутри платформ принадлежащих Корпорации " +
            " Международного Торгового Союза, при условии что данная комиссия не будет выше двадцати процентов (20%). " +
            " Все сборы должны быть направлены на расходы которые установлены бюджетом. " +
            " Также источником дохода является продажа своих товаров и услуг, для этого есть Директора офисов которые избираются " +
            " советом директоров и они должны реализовать продукцию Корпорации Международного Торгового Союза. " +
            "" ;

    //правила для законов
    //TODO
    String HOW_LAWS_ARE_CHOSEN =  " КАК ИЗБИРАЮТСЯ ЗАКОНЫ. " +
            " Ни один закон не имеет обратной силы. Ни один закон не должен нарушать действующий устав или противоречит " +
            " другим действующим законам. Если есть противотечение между несколькими законами из одного пакета законов, " +
            " то действующим является тот который списке находится выше по индексу. Пример: пакет по продаже алкоголя " +
            " закон под индексом 3 противоречит закону из индекса 17, в даном случае закон под индексом три будет действующим, так как он более выше по статусу. " +
            " если законы противоречат из разных пакетов, то действующим является тот пакет, который получил больше голосов " +
            " от Совета акционеров, если есть паритет, то тот который получил больше голосов Совета Директоров, если и здесь " +
            " есть паритет то данный спор должен решить Верховный Судья, если он также не определил который из двух пакетов " +
            " где законы противоречат друг другу является законы одно из пакетов более действующими, то в приоритете становиться " +
            " тот который начал действовать раньше, отсчет определяется именно с последнего момента вступления в силу. " +
            " Все обычные законы являются действующими если за них проголосовали таким способом ONE_VOTE Совет Акционеров, Совет Директоров и возможно " +
            " Верховный Судья. Чтобы закон был действующим он должен получить равно или больше 100 остатка голосов Совета Акционеров, " +
            " равно или больше 15 остатка голосов Совета Директоров и Один голос Верховного Судьи, но если Верховный Судья не проголосовал или проголосовал против " +
            " то можно обойти вето верховного судьи получив 200 и более остаток голосов Совета Акционеров и 30 и более остаток голосов Совета Директоров. " +
            " " +
            " Закон является действующим пока он соответствует количеству голосов как описано выше. Каждый раз как кто то теряет свою должность " +
            " также теряется все его голоса за все законы которые он проголосовал." +
            "" +
            " Пример кода в LawsController current law:" +
            " //законы которые получили не достаточно голосов которые могут пройти только если верховный судья одобрит\n" +
            "        List<CurrentLawVotesEndBalance> notEnoughVotes = current.stream()\n" +
            "                .filter(t -> !directors.contains(t.getPackageName()))\n" +
            "                .filter(t->!Seting.AMENDMENT_TO_THE_CHARTER.equals(t.getPackageName()))\n" +
            "                .filter(t->!directors.isCabinets(t.getPackageName()))\n" +
            "                .filter(t -> !Seting.ORIGINAL_CHARTER_CURRENT_LAW_PACKAGE_NAME.equals(t.getPackageName()))\n" +
            "                .filter(t -> t.getVotesBoardOfShareholders() >= Seting.ORIGINAL_LIMIT_MIN_VOTE_BOARD_OF_SHAREHOLDERS)\n" +
            "                .filter(t -> t.getVotesBoardOfDirectors() >= Seting.ORIGINAL_LIMIT_MIN_VOTE_BOARD_OF_DIRECTORS)\n" +
            "                .filter(t -> t.getVoteHightJudge() >= Seting.ORIGINAL_LIMIT_MIN_VOTE_HIGHT_JUDGE)\n" +
            "                .sorted(Comparator.comparing(CurrentLawVotesEndBalance::getVotes).reversed()).collect(Collectors.toList());\n" +
            "\n" +
            "\n" +
            "        //законы которые получили достаточно голосов и не требуют одобрения верховного судьи\n" +
            "        List<CurrentLawVotesEndBalance> powerfulVotes = current.stream()\n" +
            "                .filter(t -> !directors.contains(t.getPackageName()))\n" +
            "                .filter(t-> !Seting.AMENDMENT_TO_THE_CHARTER.equals(t.getPackageName()))\n" +
            "                .filter(t-> !directors.isCabinets(t.getPackageName()))\n" +
            "                .filter(t -> !Seting.ORIGINAL_CHARTER_CURRENT_LAW_PACKAGE_NAME.equals(t.getPackageName()))\n" +
            "                .filter(t -> t.getVotesBoardOfShareholders() >= (Seting.ORIGINAL_LIMIT_MIN_VOTE_BOARD_OF_SHAREHOLDERS * Seting.POWERFUL_VOTE))\n" +
            "                .filter(t -> t.getVotesBoardOfDirectors() >= (Seting.ORIGINAL_LIMIT_MIN_VOTE_BOARD_OF_DIRECTORS * Seting.POWERFUL_VOTE))\n" +
            "                .sorted(Comparator.comparing(CurrentLawVotesEndBalance::getVotes).reversed()).collect(Collectors.toList()); ";

    String HOW_THE_BOARD_OF_DIRECTORS_IS_ELECTED = " Как избирается Совет Директоров." +
            "" +
            " Совет директоров состоит из 301 счетов BOARD_OF_DIRECTORS. " +
            " Каждый участник сети может подать на должность совета директоров, создав пакет закона, где " +
            " название пакета BOARD_OF_DIRECTORS и счет отправителя должен совпадать счетом который указан " +
            " в первой строке закона который содержится в списке данного пакета. " +
            " 301 счет с наибольшим количеством остатка голосов получает должность. " +
            " Стоимость подачи на создание закона(должность) стоит пять цифровых долларов (5) в качестве вознаграждения добытчику. " +
            " Процесс голосования описан в VOTE_STOCK " +
            "" +
            " Пример кода: LawController: method currentLaw: " +
            " участок кода отвечающий за избрание совета директоров " +
            "  //минимальное значение количество положительных голосов для того чтобы закон действовал, \n" +
            "        //позиции избираемые акциями членов Совета Директоров\n" +
            "        List<CurrentLawVotesEndBalance> electedByStockBoardOfDirectors = current.stream()\n" +
            "                .filter(t -> directors.isElectedByStocks(t.getPackageName()))\n" +
            "                .filter(t -> t.getPackageName().equals(NamePOSITION.BOARD_OF_DIRECTORS.toString()))\n" +
            "                .filter(t -> t.getVotes() >= Seting.ORIGINAL_LIMIT_MIN_VOTE)\n" +
            "                .sorted(Comparator.comparing(CurrentLawVotesEndBalance::getVotes).reversed())\n" +
            "                .limit(directors.getDirector(NamePOSITION.BOARD_OF_DIRECTORS.toString()).getCount())\n" +
            "                .collect(Collectors.toList()); ";

    String POWERS_OF_THE_BOARD_OF_SHAREHOLDERS = " Полномочия совета акционеров.  " +
            " Совет Акционеров Участвует в утверждении Законов (правил которые должны соблюдать все участники данной Корпорации). " +
            " Также Совет Акционеров участвует в утверждении поправок в устав Корпорации Международного Торгового Союза AMENDMENT_TO_THE_CHARTER. " +
            " Совет Акционеров также может участвовать в голосовании при избрании кандидатов CORPORATE_COUNCIL_OF_REFEREES и BOARD_OF_DIRECTORS " +
            " используя эти правила для голосования за кандидатов VOTE_STOCK. " +
            "";

    String HOW_SHAREHOLDERS_BOARD_IS_ELECTED = " КАК ИЗБИРАЕТСЯ СОВЕТ АКЦИОНЕРОВ. " +
            " Совет Акционеров состоит из тысячи пятьсот счетов (1500) с наибольшим количеством акций, но учитываются только те счета " +
            " от чьей активности не прошло больше года. формула: текущий год - один год, и если счет был активен в этом диапазоне, он " +
            " учитывается. Все счета сортируются по убыванию количества цифровых акций, и отбираются 1500 счетов с наибольшим количеством  " +
            " акций. Перерасчет происходит Каждый блок. " +
            "" +
            "  Пример участка кода как избирается Совет Акционеров: " +
            " class UtilsGovernment method findBoardOfShareholders: " +
            "  //определение совета акционеров\n" +
            "    public static List<Account> findBoardOfShareholders(Map<String, Account> balances, List<Block> blocks, int limit) {\n" +
            "        List<Block> minersHaveMoreStock = null;\n" +
            "        if (blocks.size() > limit) {\n" +
            "            minersHaveMoreStock = blocks.subList(blocks.size() - limit, blocks.size());\n" +
            "        } else {\n" +
            "            minersHaveMoreStock = blocks;\n" +
            "        }\n" +
            "        List<Account> boardAccounts = minersHaveMoreStock.stream().map(\n" +
            "                        t -> new Account(t.getMinerAddress(), 0, 0))\n" +
            "                .collect(Collectors.toList());\n" +
            "\n" +
            "        for (Block block : minersHaveMoreStock) {\n" +
            "            for (DtoTransaction dtoTransaction : block.getDtoTransactions()) {\n" +
            "                boardAccounts.add(new Account(dtoTransaction.getSender(), 0, 0));\n" +
            "            }\n" +
            "\n" +
            "        }\n" +
            "\n" +
            "\n" +
            "        CompareObject compareObject = new CompareObject();\n" +
            "\n" +
            "        List<Account> boardOfShareholders = balances.entrySet().stream()\n" +
            "                .filter(t -> boardAccounts.contains(t.getValue()))\n" +
            "                .map(t -> t.getValue()).collect(Collectors.toList());\n" +
            "\n" +
            "\n" +
            "        boardOfShareholders = boardOfShareholders\n" +
            "                .stream()\n" +
            "                .filter(t -> !t.getAccount().startsWith(Seting.NAME_LAW_ADDRESS_START))\n" +
            "                .filter(t -> t.getDigitalStockBalance() > 0)\n" +
            "                .sorted(Comparator.comparing(Account::getDigitalStockBalance).reversed())\n" +
            "                .collect(Collectors.toList());\n" +
            "\n" +
            "        boardOfShareholders = boardOfShareholders\n" +
            "                .stream()\n" +
            "                .limit(Seting.BOARD_OF_SHAREHOLDERS)\n" +
            "                .collect(Collectors.toList());\n" +
            "\n" +
            "        return boardOfShareholders;\n" +
            "    } ";

    String VOTE_STOCK = " Как с помощью акций происходит голосование. " +
            " Все акции которым счет владеет, приравниваются такому же количеству голосов. " +
            " каждый раз когда кто то делает транзакцию на счет, который является адресом пакета который начинается с " +
            " LIBER он голосует за данный пакет. Учитываются только те голоса, с которых не прошло больше четырех лет. " +
            " если транзакция была совершена VoteEnum.YES то данный счет получает голоса за по формуле " +
            " yesV = количество голосов равные количеству акций отправителя." +
            " yesN = за сколько законов данный счет проголосовал с VoteEnum.YES" +
            " resultYES = yesV / math.pow(yesN, 3). Пример: счет проголосовал за три счета которые начинаются с LIBER," +
            " на счету сто акций, значит сто голосов. 100 / math.pow(3, 3) = 3.7 значит каждый счет получит по 3.7 голоса. " +
            "" +
            " если транзакция была совершена с VoteEnum.NO " +
            " то используется такая же формула, но учитываются теперь все счета за которые он проголосовал против " +
            " пример тот же счет проголосовал за два счет против, у него те же сто акций. " +
            " resultNO = noV / math.pow(noN, 3) = 100/ math.pow(2,3) = 12.5 значит каждый счет за который он проголосовал, " +
            " против получит 12.5 голосов против. " +
            " дальше каждый счет подсчитывает все отданные ему голоса ЗА (VoteEnum.YES)  и ПРОТИВ (VoteEnum.NO). " +
            " Потом используется данная формула remainder = resultYES - resultNO. " +
            " сначала данные должности отбираются все счета которые получили больше или равно четырнадцать тысяч " +
            " четыреста голосов остатка (14400) remainder >= 14400." +
            " Дальше все счета сортируются по убыванию remainder и оттуда отбираются то количество счетов на " +
            " данные должности, сколько это оговорено в данной должности. Пример: " +
            " Для Совета Директоров это 301 счет с наибольшим количеством остатка. " +
            " " +
            " В любой момент можно изменить свой голос, но только на противоположный, что значит если " +
            " вы проголосовали за кандидата YES то вы можете изменить только на NO и обратно. " +
            " Количество раз сколько вы можете изменить свой голос не ограничено. " +
            " С каждым блоком происходит перерасчет голосов, если вы теряете свои акции, ваши кандидаты " +
            " также теряют свои голоса. Данная мера специально так реализовано чтобы избираемые должности " +
            " были заинтересованы в том чтобы вы процветали. " +
            " Таким способом избираются Только CORPORATE_COUNCIL_OF_REFEREES и BOARD_OF_DIRECTORS" +
            " Учитывается только последняя транзакция отданная за каждый счет, если вы не обновляли свой голос, " +
            " то по прошествии четырех лет он аннулируется. ";

    String CODE_VOTE_STOCK = " class CurrentLawVotes method: votesLaw " +
            " public double votesLaw(Map<String, Account> balances,\n" +
            "                           Map<String, Integer> yesAverage, Map<String, Integer> noAverage) {\n" +
            "        double yes = 0.0;\n" +
            "        double no = 0.0;\n" +
            "\n" +
            "        //\n" +
            "        for (String s : YES) {\n" +
            "\n" +
            "            int count = 1;\n" +
            "            count = yesAverage.get(s) > 0 ? yesAverage.get(s) : 1;\n" +
            "            yes += balances.get(s).getDigitalStockBalance() / Math.pow(count, Seting.POWERING_FOR_VOTING);\n" +
            "\n" +
            "        }\n" +
            "        //\n" +
            "        for (String s : NO) {\n" +
            "            int count = 1;\n" +
            "            count = noAverage.get(s) > 0 ? noAverage.get(s) : 1;\n" +
            "            no += balances.get(s).getDigitalStockBalance() / Math.pow(count, Seting.POWERING_FOR_VOTING);\n" +
            "\n" +
            "        }\n" +
            "\n" +
            "\n" +
            "        return yes - no;\n" +
            "    } ";

    String POWERS_OF_DIRECTORS_IN_THE_OFFICE = " ПОЛНОМОЧИЯ ДИРЕКТОРОВ В ОФИС. " +
            " Директорами офиса, называются высшие директора которые являются директорами своих дивизионов. " +
            " Полномочия каждого директора должны быть описаны действующими законами. Но каждый директор должен управлять " +
            " только своим дивизионом. Координацией всех директоров должен руководить Генеральный Исполнительный Директор GENERAL_EXECUTIVE_DIRECTOR. " +
            " " +
            " Совет Директоров, CORPORATE_COUNCIL_OF_REFEREES, HIGH_JUDGE, Совет Акционеров и GENERAL_EXECUTIVE_DIRECTOR могут быть как " +
            " физическими лицами, так и юридическими лицами, но один счет будет учитываться как один голос. ";

    String HOW_OFFICE_DIRECTORS_ARE_CHOSEN = " КАК ИЗБИРАЮТСЯ ОФИСНЫЕ ДИРЕКТОРА. " +
            " Все Директора офиса, это высшие директора которые управляют своими дивизионами, избираются только Советом Директоров. " +
            " Каждый участник сети может подать на должность высшего директора, создав закон, с названием пакета который совпадает с допустимыми " +
            " должностями, где адрес отправителя данной транзакции должен совпадать с первой строкой из списка законов данного пакета. " +
            " стоимость закона пять цифровых долларов в качестве вознаграждения добытчику.  " +
            " счет с наибольшим количеством голосов остатка получает данную должность. " +
            " Механизм голосования описан ONE_VOTE. " +
            "" +
            " Пример участка кода как избирается должности class LawsController: method currentLaw: " +
            "" +
            " //позиции созданные советом директоров\n" +
            "        List<CurrentLawVotesEndBalance> createdByBoardOfDirectors = current.stream()\n" +
            "                .filter(t->t.getPackageName().startsWith(Seting.ADD_DIRECTOR))\n" +
            "                .filter(t->t.getVotesBoardOfDirectors() >= Seting.ORIGINAL_LIMIT_MIN_VOTE_BOARD_OF_DIRECTORS)\n" +
            "                .collect(Collectors.toList());\n" +
            "        //добавление позиций созданных советом директоров\n" +
            "        for (CurrentLawVotesEndBalance currentLawVotesEndBalance : createdByBoardOfDirectors) {\n" +
            "            directors.addAllByBoardOfDirectors(currentLawVotesEndBalance.getLaws());\n" +
            "        }\n" +
            "\n" +
            "        //позиции избираемые только советом директоров\n" +
            "        List<CurrentLawVotesEndBalance> electedByBoardOfDirectors = current.stream()\n" +
            "                .filter(t -> directors.isElectedByBoardOfDirectors(t.getPackageName()) || directors.isCabinets(t.getPackageName()))\n" +
            "                .filter(t -> t.getVotesBoardOfDirectors() >= Seting.ORIGINAL_LIMIT_MIN_VOTE_BOARD_OF_DIRECTORS)\n" +
            "                .sorted(Comparator.comparing(CurrentLawVotesEndBalance::getVotesBoardOfDirectors).reversed())\n" +
            "                .collect(Collectors.toList());";

    String ONE_VOTE = " ОДИН ГОЛОС. " +
            "" +
            " Когда голосуют данные должности, учитывается как один счет = один голос " +
            " (CORPORATE_COUNCIL_OF_REFEREES, BOARD_OF_DIRECTORS, GENERAL_EXECUTIVE_DIRECTOR, HIGH_JUDGE и Совет Акционеров). " +
            " каждый счет который начинается с LIBER учитывает все голоса ЗА (VoteEnum.YES) и ПРОТИВ (VoteEnum.NO) за него " +
            " дальше отнимается от ЗА - ПРОТИВ = если остатков выше порога, то он становиться действующим законом, но если избирается должности " +
            " то после сортируется от наибольшего к наименьшим и отбираются то количество наибольших, которое описано для данной должности. " +
            " Перерасчет голосов происходит каждый блок. После голосования голос можно поменять только на противоположный. " +
            " Ограничений на количество сколько раз можно поменять свой голос нет. Учитываются только те голоса которые даны счетами " +
            " находящимися в своей должности, к примеру если счет перестал быть в Совете Директор, его голос как в качестве " +
            " Совета Директоров не учитывается, и не будет учитываться в голосовании. Все голоса действуют, пока счета " +
            " проголосовавшие находятся в своих должностях. Учитываются также только те голоса, от которых прошло не более " +
            " четырех лет, но каждый участник, может в любой момент времени обновить свой голос.  ";

    String CODE_VOTE_ONE = " КОД class CurrentLawVotes: method voteGovernment " +
            "" +
            " public int voteGovernment(\n" +
            "            Map<String, Account> balances,\n" +
            "            List<String> governments\n" +
            "\n" +
            "    ) {\n" +
            "        int yes = 0;\n" +
            "        int no = 0;\n" +
            "\n" +
            "        List<String> addressGovernment = governments;\n" +
            "        for (String s : YES) {\n" +
            "            if (addressGovernment.contains(s)) {\n" +
            "                yes += Seting.VOTE_GOVERNMENT;\n" +
            "            }\n" +
            "\n" +
            "        }\n" +
            "        for (String s : NO) {\n" +
            "            if (addressGovernment.contains(s)) {\n" +
            "                no += Seting.VOTE_GOVERNMENT;\n" +
            "            }\n" +
            "\n" +
            "        }\n" +
            "\n" +
            "\n" +
            "        return yes - no;\n" +
            "\n" +
            "    } ";

    String MECHANISM_FOR_REDUCING_THE_NUMBER_OF_SHARES = " МЕХАНИЗМ СНИЖЕНИЯ КОЛИЧЕСТВА АКЦИЙ. " +
            " Каждый раз когда один счет отправляет на другой счет цифровую акцию, но использует VoteEnum.NO, счет " +
            " цифровых акций получателя снижается на то количество которое отправил отправитель акций. " +
            " Пример счет А отправил на счет Б 100 цифровых акций с VoteEnum.NO, тогда счет А и счет Б оба теряют 100 " +
            " цифровых акций. Данная мера нужна чтобы был механизм снять с должности Совета акционеров и также позволяет снижать голоса " +
            " деструктивных счетов, так как количество голосов, равно количеству акций, при Избрании Совета Директоров и " +
            " при избрании CORPORATE_COUNCIL_OF_REFEREES. " +
            " Данный механизм действует только на цифровые акции и только в том случае, что отправитель отправил с знаком " +
            " VoteEnum.NO ";

    String WHO_HAS_THE_RIGHT_TO_CREATE_LAWS = " Создавать законы в криптовалюте Корпорации Международный Торговый Союз имеют права " +
            " все участники сети, которые имеют минимум пять цифровых долларов. " +
            " Для создания закона через механизм криптовалюты Корпорации Международного торгового Союза " +
            " Нужно внутри данной криптовалюты Создать объект класса Laws, где packetLawName - является названием пакета законов. " +
            " List<String> laws - является списком законов, String hashLaw - является адресом данного пакета законов и начинается с LIBER. " +
            " Чтобы Закон попал в пул законов нужно создать транзакцию где получателем является hashLaw данного закона и вознаграждение " +
            " майнера равно пять цифровых доллара (5)  данной криптовалюты. После этого как закон попадет в блок, он окажется в пуле " +
            " законов и за него можно будет голосовать. " +
            " Количество строк в пакете законов может быть столько, сколько понадобиться и нет никаких ограничений. ";

    String POWERS_OF_THE_CORPORATE_COUNCIL_OF_JUDGES = " ПОЛНОМОЧИЯ КОРПОРАТИВНОГО СОВЕТА СУДЕЙ. " +
            " Утверждает Верховного судью. " +
            " Участвует в голосовании внедрения поправок. " +
            " " +
            " Судебная власть Корпорации Международного Торгового Союза принадлежит " +
            " одному Верховному суду и таким нижестоящим судам, которые Корпорация Международный " +
            " Торговый Союз может время от времени издавать и учреждать. " +
            " Судьи как верховных, так и нижестоящих судов занимают свои должности при хорошем поведении и " +
            " в установленные сроки получают за свои услуги вознаграждение. " +
            " Судебная власть распространяется на все дела по закону и справедливости, " +
            " в том числе инициированные членами для оспаривания незаконного расходования средств, " +
            " возникающего в соответствии с настоящем Уставом, законами Корпорации Международного Торгового Союза и договорами, " +
            " заключенными или которые будут заключены в соответствии с их авторитетом. " +
            " К спорам, " +
            " в которых Международный Торговый Союз будут стороной к разногласиям между двумя или более участников сети. " +
            " Ни один суд не должен быть тайным, но правосудие должно вершиться открыто и бесплатно, полностью и безотлагательно, " +
            " и каждый человек должен иметь правовую правовую защиту от вреда, причиненного жизни, свободе или имуществу. " +
            " Верховный Суд CORPORATE_COUNCIL_OF_REFEREES и верховный судья HIGH_JUDGE ";

    String HOW_THE_CORPORATE_BOARD_OF_JUDGES_IS_ELECTED = " КАК ИЗБИРАЮТСЯ КОРПОРАТИВНЫЙ СОВЕТ СУДЕЙ. " +
            " CORPORATE_COUNCIL_OF_REFEREES " +
            "  состоит из 55 счетов. " +
            " каждый участник сети может подать на должность CORPORATE_COUNCIL_OF_REFEREES , создав пакет закона, где " +
            " название пакета CORPORATE_COUNCIL_OF_REFEREES и счет отправителя должен совпадать счетом который указан " +
            " в первой строке закона который содержится в списке данного пакета " +
            " 55 счет с наибольшим количеством остатка голосов получает должность. " +
            " стоимость подачи на создание закона(должность) стоит пять цифровых долларов (5) в качестве вознаграждения добытчику. " +
            " Процесс голосования описан в VOTE_STOCK " +
            "" +
            " Пример участка кода: class LawsController: method currentLaw: " +
            "  //минимальное значение количество положительных голосов для того чтобы закон действовал,\n" +
            "        //позиции избираемые акциями CORPORATE_COUNCIL_OF_REFEREES\n" +
            "        List<CurrentLawVotesEndBalance> electedByStockCorporateCouncilOfReferees = current.stream()\n" +
            "                .filter(t -> directors.isElectedByStocks(t.getPackageName()))\n" +
            "                .filter(t -> t.getPackageName().equals(NamePOSITION.CORPORATE_COUNCIL_OF_REFEREES.toString()))\n" +
            "                .filter(t -> t.getVotes() >= Seting.ORIGINAL_LIMIT_MIN_VOTE)\n" +
            "                .sorted(Comparator.comparing(CurrentLawVotesEndBalance::getVotes).reversed())\n" +
            "                .limit(directors.getDirector(NamePOSITION.CORPORATE_COUNCIL_OF_REFEREES.toString()).getCount())\n" +
            "                .collect(Collectors.toList());\n";

    String HOW_THE_CHIEF_JUDGE_IS_CHOSEN = " КАК ИЗБИРАЕТСЯ ВЕРХОВНЫЙ СУДЬЯ HIGH_JUDGE. " +
            " Верховный Судья избирается CORPORATE_COUNCIL_OF_REFEREES. " +
            " Каждый участник сети может подать на должность Верховного Судьи, создав закон, с названием пакета который совпадает с допустимым " +
            " должностью, где адрес отправителя данной транзакции должен совпадать с первой строкой из списка законов данного пакета. " +
            " стоимость закона пять цифровых долларов в качестве вознаграждения добытчику.  " +
            " счет с наибольшим количеством голосов остатка получает данную должность. " +
            " Механизм голосования описан ONE_VOTE. " +
            "" +
            " Пример кода как утверждается верховный судья. class LawsController: method currentLaw. Участок кода " +
            "   //позиции избираемые советом корпоративных верховных судей\n" +
            "        List<CurrentLawVotesEndBalance> electedByCorporateCouncilOfReferees = current.stream()\n" +
            "                .filter(t -> directors.isElectedBYCorporateCouncilOfReferees(t.getPackageName()))\n" +
            "                .filter(t -> t.getVotesCorporateCouncilOfReferees() >= Seting.ORIGINAL_LIMIT_MIN_VOTE_CORPORATE_COUNCIL_OF_REFEREES)\n" +
            "                .sorted(Comparator.comparing(CurrentLawVotesEndBalance::getVotesCorporateCouncilOfReferees)).collect(Collectors.toList()); ";

    String POWERS_OF_THE_CHIEF_JUDGE = " ПОЛНОМОЧИЯ ВЕРХОВНОГО СУДЬИ. " +
            " Верховный судья участвует в утверждении законов, а также " +
            " может участвовать в решении споров внутри членов сети как и CORPORATE_COUNCIL_OF_REFEREES, " +
            " но его голос выше чем голос CORPORATE_COUNCIL_OF_REFEREES. ";

    String HOW_IS_THE_PROCESS_OF_AMENDING_THE_CHARTER = " КАК ПРОИСХОДИТ ПРОЦЕСС ВНЕСЕНИЯ ПОПРАВОК В УСТАВ. " +
            " Для внесения поправок, нужно создать закон с названием пакета AMENDMENT_TO_THE_CHARTER, " +
            " дальше за этот закон должны проголосовать методом описанным в VOTE_ONE " +
            " Совет Акционеров и остаток голосов должен быть равен или выше 300 участников, " +
            " также должны проголосовать Совет Директоров и остаток голосов должен быть 60 или больше, " +
            " также должны проголосовать корпоративные верховные судьи (CORPORATE_COUNCIL_OF_REFEREES) и " +
            " остаток голосов должен быть равен или больше 5. " +
            " Но поправки не должны касаться способа установления правил действующих законов, а также " +
            " избрания Совета Директоров, Совета Акционеров, Генерального Исполнительного Директора, " +
            " Совета Корпоративных Судей и Верховного Судьи. Поправки могут изменять код, если сохраняются правила " +
            " избрания действующих должностей (включая правил голосования), законов и добычи денег (добыча цифровых долларов и цифровых акции)," +
            " Ни одна поправка не должна наделять из выше перечисленных должностей большей властью. " +
            " Также поправки не должны ущемлять Естественные Права Человека. " +
            " " +
            " Пример кода. class LawsController: method currentLaw: участок кода утверждающий действующие поправки" +
            "  //внедрение поправок в устав\n" +
            "        List<CurrentLawVotesEndBalance> chapter_amendment = current.stream()\n" +
            "                .filter(t -> !directors.contains(t.getPackageName()))\n" +
            "                .filter(t-> Seting.AMENDMENT_TO_THE_CHARTER.equals(t.getPackageName()))\n" +
            "                .filter(t->!directors.isCabinets(t.getPackageName()))\n" +
            "                .filter(t -> t.getVotesBoardOfShareholders() >= Seting.ORIGINAL_LIMIT_MINT_VOTE_BOARD_OF_SHAREHOLDERS_AMENDMENT)\n" +
            "                .filter(t -> t.getVotesBoardOfDirectors() >= Seting.ORIGINAL_LIMIT_MIN_VOTE_BOARD_OF_DIRECTORS_AMENDMENT)\n" +
            "                .filter(t -> t.getVotesCorporateCouncilOfReferees() >= Seting.ORIGINAL_LIMIT_MIN_VOTE_CORPORATE_COUNCIL_OF_REFEREES_AMENDMENT)\n" +
            "                .sorted(Comparator.comparing(CurrentLawVotesEndBalance::getVotesBoardOfDirectors).reversed()).collect(Collectors.toList());\n ";

    String HOW_THE_BUDGET_IS_APPROVED = " КАК УТВЕРЖДАЕТСЯ БЮДЖЕТ. " +
            " Действующий бюджет может быть только один. Бюджет утверждает только Совет Директоров. " +
            " Для утверждения бюджета нужно получить методом описанным в VOTE_ONE 15 и больше голосов. " +
            " сам процесс происходит так: " +
            " 1. Сначала отбираются все пакеты законов где название пакета совпадает с BUDGET. " +
            " 2. Дальше отбираются все пакеты которые остаток голосов получили 15 или больше. " +
            " 3. Дальше все эти пакеты сортируются по убыванию, с наибольшим количеством голосов. " +
            " 4. Дальше отбирается самый первый с наибольшим количеством голосов. " +
            "" +
            " Пример кода утверждающий бюджет. class LawsController: method: currentLaw. " +
            "   //бюджет утверждается только советом директоров.\n" +
            "        List<CurrentLawVotesEndBalance> budjet = current.stream()\n" +
            "                .filter(t-> !directors.contains(t.getPackageName()))\n" +
            "                .filter(t->Seting.BUDGET.equals(t.getPackageName()))\n" +
            "                .filter(t->!directors.isCabinets(t.getPackageName()))\n" +
            "                .filter(t-> t.getVotesBoardOfDirectors() >= Seting.ORIGINAL_LIMIT_MIN_VOTE_BOARD_OF_DIRECTORS)\n" +
            "                .sorted(Comparator.comparing(CurrentLawVotesEndBalance::getVotesBoardOfDirectors).reversed())\n" +
            "                .limit(1)\n" +
            "                .collect(Collectors.toList());";



    String HOW_IS_THE_STRATEGIC = " КАК УТВЕРЖДАЕТСЯ СТРАТЕГИЧЕСКИЙ ПЛАН. " +
            " Стратегический план утверждает Совет Директоров, стратегический план может быть действующим " +
            " только в единственном экземпляре. Чтобы Стратегический План был действующим, нужно остаток голосов " +
            " Совета Директоров 15 или больше. Способ голосования VOTE_ONE. " +
            "  " +
            " Совет Директоров в любой момент может отменить Стратегический План. Стратегический План действует " +
            " пока количество голосов 15 или больше. Стратегический План может включать в себя общее направление " +
            " Корпорации, а также что нужно реализовать. " +
            "" +
            " участок кода который показывает как утверждается Стратегический План. " +
            " class LawsController: method currentLaw: " +
            " \n" +
            "        //план утверждается только палатой Советом Директоров\n" +
            "        List<CurrentLawVotesEndBalance> planFourYears = current.stream()\n" +
            "                .filter(t->!directors.contains(t.getPackageName()))\n" +
            "                .filter(t->Seting.STRATEGIC_PLAN.equals(t.getPackageName()))\n" +
            "                .filter(t->!directors.isCabinets(t.getPackageName()))\n" +
            "                .filter(t->t.getVotesBoardOfDirectors() >= Seting.ORIGINAL_LIMIT_MIN_VOTE_BOARD_OF_DIRECTORS)\n" +
            "                .sorted(Comparator.comparing(CurrentLawVotesEndBalance::getVotesBoardOfDirectors).reversed())\n" +
            "                .limit(1)\n" +
            "                .collect(Collectors.toList());";



    String HOW_NEW_POSITIONS_ARE_ADDED = " КАК ДОБАВЛЯЮТСЯ НОВЫЕ ДОЛЖНОСТИ. " +
            " Таким способом добавляются только высшее руководство, подчиненные каждого руководителя " +
            " нанимают без использования блокчейна, их может нанять сам директор, или иным способом как " +
            " описано действующими законами. Высшее руководство добавляется в список class Directors. " +
            " все новые добавленные должности действуют пока их законы которые создают данные должности " +
            " действуют. Добавлять новые должности может только Совет директоров. " +
            " Каждый пакет законов который начинается с ADD_DIRECTOR определяется как добавление должности. " +
            " список законов который находиться внутри данного пакета являются должностями если название в " +
            " строке начинается с ADD_DIRECTOR. Пример: название пакета ADD_DIRECTOR_PACKAGE" +
            " название первой третьей и четвертой строки ADD_DIRECTOR_FIRST ADD_DIRECTOR_THIRD " +
            " ADD_DIRECTOR_FOUR таким образом будет созданы три места для должности. " +
            " Но если строка начинается с ADD_DIRECTOR, то название должности должно быть большими " +
            " буквами и нижними подчеркиваниями, так же в одной строке должна быть только одна должность " +
            " и больше никаких слов. Те строки где нет добавления должности, используются для описания " +
            " полномочий добавленных должностей. " +
            " Чтобы новые должности были добавлены в список, Совет Директоров должен проголосовать методом " +
            " VOTE_ONE 15 или больше голосов. " +
            " После как новые должности будут созданы, каждый участник сети сможет подавать себя на данные должности. " +
            "" +
            " пример участка кода который создает новые должности. " +
            " class LawsController: method currentLaw: " +
            "" +
            " \n" +
            "        //добавляет законы, которые создают новые должности директоров\n" +
            "        List<CurrentLawVotesEndBalance> addDirectors = current.stream()\n" +
            "                .filter(t->t.getPackageName().startsWith(Seting.ADD_DIRECTOR))\n" +
            "                .filter(t->t.getVotesBoardOfDirectors() >= Seting.ORIGINAL_LIMIT_MIN_VOTE_BOARD_OF_DIRECTORS)\n" +
            "                .collect(Collectors.toList());";



    String PROPERTY_OF_THE_CORPORATION = "  СОБСТВЕННОСТЬ КОРПОРАЦИИ. " +
            " Вся собственность которая принадлежит Корпорации Международного Торгового Союза, не может быть продана без действующего закона, " +
            " где будет описан процесс продажи и по какой стоимости будет продана собственность. Счет основателя, и счета других участников не является " +
            " счетом корпорации, Совет Директоров должен создать отдельный счет который будет  бюджетом и управляться только членами действующих членов " +
            " Совета Директоров. ";

    String INTERNET_STORE_DIRECTOR = " Данный директор занимается разработкой, продвижением и руководством интернет магазина, в котором должны " +
            " продаваться товары за цифровой доллар или цифровые акции. Детальные полномочия должны быть данны или через действующие законы или " +
            " выданы Генеральным исполнительным директором или Советом Директоров. " +
            "Название Магазина должен определить либо Совет Директоров или Генеральный Исполнительный Директор. ";

    String GENERAL_EXECUTIVE_DIRECTOR = " Данный Директор координирует действия остальных высших директоров для реализации стратегического плана или " +
            " поставленных перед ним задач действующими законами. Все полномочия должны быть ему выданы через действующие законы. ";

    String DIRECTOR_OF_THE_DIGITAL_EXCHANGE = " Данный Директор разработкой, продвижением и руководством интернет биржа. Полномочия должны быть " +
            " выданы ему или действующими законами, или Советом Директоров или или Генеральным Исполнительным Директором. ";

    String DIRECTOR_OF_DIGITAL_BANK = " Данный Директор руководит интернет банком. Все полномочия должны быть выданы или действующими законами, или Советом директоров " +
            " или Генеральным Исполнительным Директором. ";

    String DIRECTOR_OF_THE_COMMERCIAL_COURT = " Директор частного коммерческого суда должен обеспечивать руководство частным судом, все полномочия " +
            " должны быть Выданы или действующими законами или Советом Директоров или Генеральным Исполнительным Директором. ";

    String MEDIA_DIRECTOR = " Данный Директор руководит сми, все полномочия должны быть выданы действующими законами, или Советом Директоров или " +
            " генеральным исполнительным Директором. ";

    String DIRECTOR_OF_THE_DEVELOPMENT_OF_THE_IMPLEMENTATION_OF_CRYPTOCURRENCY_TECHNOLOGIES_OF_THE_INTERNATIONAL_TRADE_UNION_CORPORATION = "" +
            " Данный Директор руководит разработкой и внедрением нового кода в данную криптовалюту, все полномочия должны быть выданы только через " +
            " действующие законы, но также могут быть выданы или Советом Директоров, если действующие законы дали такие полномочия Совету Директоров. " +
            " Также ни одно изменение кода не должно противоречить действующему уставу или действующим законам, Также полномочия могут быть выданы " +
            " Генеральным Исполнительным Директором, если генеральному исполнительному директору данные полномочия выданы действующим законами. ";


    String EXPLANATION_WHY_MONEY_DEMURAGE_IS_USED_HERE = " ОБЯСНЕНИЕ ПОЧЕМУ ЗДЕСЬ ИСПОЛЬЗУЕТСЯ  ДЕМЕРЕДЖ ДЕНЕГ.." +
            " Отрицательная ставка сейчас применяется во множестве стран, данная стимулирует держателей денег, когда цена чрезмерно завышена, " +
            " насыщать рынок деньгами. Количество добываемых денег за каждый блок составляет 200 цифровых долларов и 200 цифровых акций, " +
            " также 2% от каждой добычи вознаграждение основателю, что составляет 4 цифровых доллара и 4 цифровых Акций при каждой добыче блока. " +
            " Здесь используется как Теории сильвио Гезеля, а также школы монетаризма (в измененном виде " +
            " у сильвио Гезеля, отрицательная ставка составляла 1% в месяц, что просто убило бы экономику, " +
            " при монетаризме рост денежной массы должен был быть пропорционален росту ВВП, но так как " +
            " в данной системе не получиться посчитать реальный рост ВВП, я установил фиксированный рост, также если денежный рост " +
            " будет равен ВВП, есть высокая вероятность Гиперинфляции. Деньги должны быть твердые, чтобы " +
            " бизнес мог прогнозировать свои долгосрочные вложения и от монетаризма, взята только та часть что " +
            " денежная масса должна расти линейно, но в целом здесь микс из разных экономических школ, включая Австрийскую " +
            " экономическую школу.). При отрицательной ставке 0.1% каждые пол года для цифровых " +
            " долларов и 0.2% для цифровых акций мы избегаем последствий тяжелого экономического кризиса для данной валюты. " +
            " Такой механизм создает коридор цен, где нижняя граница стоимости данных цифровых валют является общее количество выпущенных цифровых " +
            " долларов и цифровых акций, а верхняя граница является реальная стоимость. Так как только стоимость становиться выше реальной стоимости, " +
            " держателям выгодней становиться продавать цифровые доллары и цифровые акции, по завышенным ценам, тем самым насыщая рынок деньгами " +
            " и создавая коррекцию на рынке. " +
            "" +
            " Основным источником монетарных кризисов, является между быстрыми изменениями цен на товары и медленным изменением заработных плат. " +
            " Пример: Представим что стоимость валюты резко подорожало на 30%, держателям выгодней становиться не инвестировать деньги, так как " +
            " доходы от удерживания валюты, выше чем теперь уже оплачивать более дорогих сотрудников, из за того деньги перестают " +
            " инвестироваться, люди не дополучают заработные платы, что приводит к тому, что огромное количество товаров не реализуется, " +
            " что приводит к тому, часть производителей банкротится и увольняют множество рабочих, что еще больше снижает заработную " +
            " плату у оставшихся, так как становиться профицитный рынок труда. Что  в свою очередь еще больше вызывает страх у держателей " +
            " денег инвестировать и данный процесс продолжается до того момента, пока стоимость денег не начинает сокращаться в связи с тем " +
            " что общее количество производственных цепочек сократилось и также сократились товары. " +
            " Пример: Представим что у нас произошла инфляция и стоимость денег упала на 40% в течение месяца, стоимость товаров резко возрастает, " +
            " но заработные платы не выросли, таким образом множество товаров не будут куплены, что приводит к закрытию производственных цепочек, " +
            " что в свою очередь из за избытка рабочих на рынке труда, снижает заработную плату, что также в свою очередь еще больше сокращает " +
            " количество проданных товаров. Первый случай Дефляционная спираль возникает из за резкого сокращения денег на рынке, второй " +
            " случай стагфляция чаще возникает когда на рынок поступает резко избыточное количество денег. " +
            "" +
            " Чтобы не возникали такие кризисы не возникали, в данной криптовалюте деньги прирастают в одинаковом предсказуемом количестве. " +
            " 204 (4 - вознаграждение основателю, 200 - вознаграждение добытчику) " +
            " цифровых долларов и акций за блок, в сутках около 576 блоков. А отрицательная ставка корректирует стоимость монет каждые пол года. " +
            " Также запрещено использовать частичное банковское резервирование для данных монет, так как их количество растет линейно, и " +
            " не сможет покрыть долги возникшие из за частичного банковского резервирования, в связи отсутствия с недостатком " +
            " наличности, так как при частичном банковском резервировании рост долгов будет намного выше, чем данный протокол будет создавать денег. " +
            " также если увеличить денежную массу изменив настройки, и сделав рост денежной массы значительно выше, может вызвать гиперинфляцию или " +
            " даже галопирующую инфляцию. Если нужно будет увеличить рост денежной массы это должно происходить только через внесения поправок, " +
            " сохраняя процент вознаграждения основателя в двух процентах. И добыча за блок не должна увеличиваться больше 5% в течение " +
            " двадцати лет, каждое следующее увеличение которое может вноситься должно проходить не менее двадцати лет через поправки, " +
            " и не более 5% за блок от вознаграждения последнего блока. (Пример: если мы изменили " +
            " через поправки, то добыча не должна быть выше 210 монет, но каждые следующие будет не больше пяти процентов от последнего. " +
            " Таким образом следующее увеличение внесенное через поправки составит 220.5 монет. Но Эту поправку внесут только через двадцать " +
            " лет после первой поправки по изменению добычи) " +
            "" +
            " При недостатки денежной массы, если не было изменено количество добываемых монет через поправку, можно добавить несколько " +
            " дополнительных нулей после запятой, таким образом это просто увеличит ценность монет, без увелечения общей выпущенной денежной массы." +
            "" +
            " Отрицательные ставки не должны быть выше 0.5% годовых и ниже 0.2% годовых. Отрицательные ставки можно изменять только через внесения поправок. " +
            "  ";

    String FREEDOM_OF_SPEECH = " Ни один орган данной корпорации или субъект не должен запрещать свободное исповедание " +
            " какой-либо религии; или ограничивать свободу слова, совести или печати; " +
            " или право людей мирно собираться или объединяться друг с другом, или не объединяться друг с другом, и " +
            " обращаться к руководству Корпорации Международного Торгового Союза и к данной корпорации с ходатайством об удовлетворении жалоб; " +
            " или нарушать право на плоды своего труда или " +
            " право на мирную жизнь по своему выбору. Свободы слова и совести включают свободу вносить вклад в " +
            " политические кампании или кандидатуры на корпоративные должности и должны толковаться как " +
            " распространяющиеся в равной степени на любые средства коммуникации. ";

    String RIGHTS = " Все члены сети, должны соблюдать Естественные Права Человека и не нарушать их. " +
            " Также должно соблюдаться презумпция невиновности и каждый участник сети должен иметь права на честное независимое " +
            " судебное разбирательство. Каждый участник имеет права на адвоката или быть самому себе адвокатом. " +
            " Корпорация Международный Торговый Союз не должна регулировать стоимость товаров и услуг участников сети, которые " +
            " продают через данную платформу. Также Корпорация не должна запрещать отдельные бренды на своей площадке, но может " +
            " запрещать продавать целые группы товаров, которые попадают по характеристикам описанных действующими законами, если " +
            " этот запрет не нарушает Естественные Права Человека. В качестве источника прав можно брать в качестве прецедента Страны признанные демократическими " +
            " странами.  " +
            "  К числу естественных неотчуждаемых прав человека относят право на жизнь, " +
            " свободу, безопасность, собственность, физическую и психическую неприкосновенность, достоинство личности, личную и семейную тайну и т. п. " +
            "" +
            " Ни один действующий закон не должен интерпретироваться так, чтобы нарушать естественные права человека. " +
            " Корпоративный верховный суд может использовать прецеденты в качестве судебных решений, если эти решения не противоречат " +
            " действующему уставу и действующим законам." +
            " Корпоративный Верховный Суд может создавать прецеденты аналогичные странам с прецедентным правом, но применять " +
            " можно если эти прецеденты не нарушают действующий устав или действующие законы Корпорации Международного Торгового Союза. ";

}
