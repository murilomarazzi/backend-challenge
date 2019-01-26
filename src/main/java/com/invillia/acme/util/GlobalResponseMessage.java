package com.invillia.acme.util;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by Murilo Marazzi on 25/01/2019.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GlobalResponseMessage {

    public enum Type {
        success, warning, danger, info
    }

    private Type type;
    private String text;
    private int code;

    public GlobalResponseMessage(Type type, String text) {
        this.type = type;
        this.text = text;
    }

    public GlobalResponseMessage(Type type, int code) {
        this.type = type;
        this.code = code;
    }

    public GlobalResponseMessage(Type type, int code, String message) {
        this.type = type;
        this.code = code;
        this.text = message;
    }

    public String getText() {
        return text;
    }

    public Type getType() {
        return type;
    }

    public int getCode() {
        return code;
    }

    public static GlobalResponseMessage success(String text) {
        return new GlobalResponseMessage(Type.success, text);
    }

    public static GlobalResponseMessage warning(String text) {
        return new GlobalResponseMessage(Type.warning, text);
    }

    public static GlobalResponseMessage danger(String text) {
        return new GlobalResponseMessage(Type.danger, text);
    }

    public static GlobalResponseMessage info(String text) {
        return new GlobalResponseMessage(Type.info, text);
    }
}
