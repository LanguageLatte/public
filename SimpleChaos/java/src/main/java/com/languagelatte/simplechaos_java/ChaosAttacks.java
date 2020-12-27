package com.languagelatte.simplechaos_java;

public interface ChaosAttacks {

  public void exception();

  // public void specificException(Exception e);
  public void error();
  // public void specificError(Error e);
  public void jvmCrash();

  public void latency();
  // public void highCPU();
  // public void highMemory();
  // public void highDisk();
  // public void highNetwork();
  // public void abnormalLogs();

  @SuppressWarnings("unchecked")
  default <T extends Throwable> void sneakyThrow(Throwable t) throws T {
    throw (T) t;
  }
}
