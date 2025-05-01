package com.javaweb.hospital.exception.handler;

public enum DBConstraintKeyword {

  PRIMARY_KEY,
  UNIQUE_KEY;

  @Override
  public String toString() {
    return switch (this) {
      case PRIMARY_KEY -> "pk";
      case UNIQUE_KEY -> "uq";
    };
  }
}
