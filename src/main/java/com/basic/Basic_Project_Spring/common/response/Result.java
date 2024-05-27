package com.basic.Basic_Project_Spring.common.response;

public record Result<E>(E data, int size) {
}
