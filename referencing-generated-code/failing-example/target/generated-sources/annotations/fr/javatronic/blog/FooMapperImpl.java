package fr.javatronic.blog;

// GENERATED CODE, DO NOT MODIFY, THIS WILL BE OVERRIDE
public class FooMapperImpl implements FooMapper {

    private final BarMapper barMapper;

    public FooMapperImpl(BarMapper barMapper) {
        this.barMapper = barMapper;
    }

    @Override
    public String map(Integer e) {
        return new Foo(this.barMapper).map(e);
    }

}
