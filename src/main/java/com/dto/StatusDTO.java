package com.dto;

import com.enums.StatusEnum;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StatusDTO<T> {
        private StatusEnum code;
        private String statusDescription;
        private T data;
}


