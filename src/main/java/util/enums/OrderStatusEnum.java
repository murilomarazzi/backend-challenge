package util.enums;

/**
 * Created by Murilo Marazzi on 25/01/2019.
 */
public enum OrderStatusEnum {

    R("Recebido"),
    P("Processado"),
    D("Despachado"),
    C("Concluido");

    private String label;

    OrderStatusEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
