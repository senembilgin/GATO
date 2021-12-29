package com.gato.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddUserServerDTO {
    private long userId;
    private long serverId;
}
