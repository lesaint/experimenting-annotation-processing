package fr.javatronic.blog;

import fr.javatronic.damapping.annotation.Mapper;

/**
 * Foo -
 *
 * @author Sébastien Lesaint
 */
@Mapper
public class Foo {
  private final BarMapper barMapper;

  public Foo(BarMapper barMapper) {
    this.barMapper = barMapper;
  }

  public String map(Integer e) {
    return null;
  }
}
