package com.cbtest.enums;

public enum MessagesEnum {
    //success
    INSERT_SUCCESS ("Inserido com sucesso."),
    UPDATE_SUCCESS ("Atualizado com sucesso."),
    DELETE_SUCCESS ("Deletado com sucesso."),

    //errors
    NO_CUSTOMER ("Cliente vazio."),
    NO_MAIN_ADDRESS ("Um cliente deve possuir ao menos um endereço principal."),
    INVALID_AGE ("O cliente deve ter menos que 100 anos."),
    INVALID_CPF ("CPF inválido."),
    CUSTOMER_EXISTS ("Já existe um cliente cadastrado com este CPF."),
    CUSTOMER_DOES_NOT_EXIST ("Não existe um cliente com esse ID."),
    ;

    private final String message;

    private MessagesEnum(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
