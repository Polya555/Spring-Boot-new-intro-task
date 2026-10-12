package mate.academy.mapper;

import mate.academy.dto.BookDto;
import mate.academy.dto.BookDtoWithoutCategoryIds;
import mate.academy.dto.CreateBookRequestDto;
import mate.academy.entity.Book;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.AfterMapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        implementationPackage = "<PACKAGE_NAME>.impl")
public interface BookMapper {
    BookDto toDto(Book book);

    Book toEntity(CreateBookRequestDto requestDto);

    BookDtoWithoutCategoryIds toDtoWithoutCategories(Book book);

    void updateBookFromDto(CreateBookRequestDto book, @MappingTarget Book entity);

    @AfterMapping
    default void setCategoryIds(@MappingTarget BookDto bookDto, Book book) {
    }
}
