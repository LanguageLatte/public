package com.languagelatte.side_effect;

import com.google.common.collect.ImmutableMap;
import java.io.Serializable;
import java.util.Set;

public final class Config implements Serializable {
  private static final long serialVersionUID = 1L;

  private final Set<String> annotatedPackages;
  private final Set<String> unAnnotatedPackages;

  public Config(ImmutableMap<String, String> flags) {

    this.annotatedPackages =
        Set.of(flags.getOrDefault("SideEffect:AnnotatedPackages", "").split(","));
    this.unAnnotatedPackages =
        Set.of(flags.getOrDefault("SideEffect:UnAnnotatedPackages", "").split(","));
  }

  public boolean isAnnotatedPackage(String p) {
    while (p.contains(".")) {
      if (this.annotatedPackages.contains(p)) {
        return true;
      }
      p = p.substring(0, p.lastIndexOf("."));
    }

    return false;
  }

  public boolean isUnAnnotatedPackage(String p) {
    while (p.contains(".")) {
      if (this.unAnnotatedPackages.contains(p)) {
        return true;
      }
      p = p.substring(0, p.lastIndexOf("."));
    }

    return false;
  }
}
