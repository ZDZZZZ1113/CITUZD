package International_Trade_Union.entity;

import lombok.Data;

@Data
public class InfoDemerageMoney {
    private String address;
    private double beforeDollar;
    private double beforeStock;
    private double afterDollar;
    private double afterStock;

    public InfoDemerageMoney(String address, double beforeDollar, double beforeStock, double afterDollar, double afterStock) {
        this.address = address;
        this.beforeDollar = beforeDollar;
        this.beforeStock = beforeStock;
        this.afterDollar = afterDollar;
        this.afterStock = afterStock;
    }

    public InfoDemerageMoney() {
    }
}
