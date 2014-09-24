package fr.javatronic.blog;

import fr.javatronic.damapping.annotation.Mapper;

/**
 * Acme -
 *
 * @author Sébastien Lesaint
 */
@Mapper
public class Acme {
  private final Miraculous m;

  public Acme(Miraculous m) {
    this.m = m;
  }

  public String map(Integer e) {
    return null;
  }
}
