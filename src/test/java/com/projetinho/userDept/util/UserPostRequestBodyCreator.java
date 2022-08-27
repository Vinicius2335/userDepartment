package com.projetinho.userDept.util;

import com.projetinho.userDept.requests.UserPostRequestBody;

public class UserPostRequestBodyCreator {
    public static UserPostRequestBody createdUserPostRequestBody(){
        return UserPostRequestBody.builder()
                .departament(UserCreator.createdUserForSave().getDepartament())
                .email(UserCreator.createdUserForSave().getEmail())
                .name(UserCreator.createdUserForSave().getName())
                .build();
    }
}
