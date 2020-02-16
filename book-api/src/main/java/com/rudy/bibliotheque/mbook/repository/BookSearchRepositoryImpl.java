package com.rudy.bibliotheque.mbook.repository;

import com.rudy.bibliotheque.mbook.model.Book;
import com.rudy.bibliotheque.mbook.search.BookSearch;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class BookSearchRepositoryImpl implements BookSearchRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Book> findAllBySearch(BookSearch bookSearch) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> query = cb.createQuery(Book.class);
        Root<Book> book = query.from(Book.class);

        List<Predicate> predicates = new ArrayList<>();

        Path<String> namePath = book.get("name");
        Path<String> isbnPath = book.get("isbn");

        if (bookSearch.getName() != null && bookSearch.getIsbn() != null) {
            predicates.add(cb.like(cb.lower(namePath), "%" + bookSearch.getName().toLowerCase() + "%"));
            predicates.add(cb.like(isbnPath, bookSearch.getIsbn() + "%"));
        } else if (bookSearch.getName() != null) {
            predicates.add(cb.like(cb.lower(namePath), "%" + bookSearch.getName() + "%"));
        } else if (bookSearch.getIsbn() != null) {
            predicates.add(cb.like(isbnPath, bookSearch.getIsbn() + "%"));
        } else {
            predicates.add(cb.like(namePath, "%"));
        }

        if (bookSearch.getAuthor() != null) {
            Path<String> authorPath = book.get("author");
            predicates.add(cb.like(cb.lower(authorPath), "%" + bookSearch.getAuthor().toLowerCase() + "%"));
        }

        if (bookSearch.getPublisher() != null) {
            Path<String> publisherPath = book.get("publisher");
            predicates.add(cb.like(cb.lower(publisherPath), "%" + bookSearch.getPublisher().toLowerCase() + "%"));
        }


        query.select(book)
                .where(cb.and(predicates.toArray(new Predicate[0])));

        return entityManager.createQuery(query)
                .getResultList();
    }
}
