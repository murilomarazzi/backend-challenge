package util.enums;

/**
 * Created by Murilo Marazzi on 25/01/2019.
 */
public enum PaymentStatusEnum {

    E("Em Analise"),
    N("Negado"),
    A("Aprovado");

    private String label;

    PaymentStatusEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
