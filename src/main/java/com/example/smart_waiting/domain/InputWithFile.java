package com.example.smart_waiting.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class InputWithFile {

    String OriginalImageName;
    String ImagePath;
}
