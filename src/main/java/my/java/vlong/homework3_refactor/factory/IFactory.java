package my.java.vlong.homework3_refactor.factory;

public interface IFactory<T, DTO> {

    public DTO toDTO(T t);

    public T toEntity(DTO t);
}
