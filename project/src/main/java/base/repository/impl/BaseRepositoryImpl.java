package base.repository.impl;

import base.domain.BaseEntity;
import base.repository.BaseRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseRepositoryImpl<E extends BaseEntity<ID>, ID extends Serializable>
        implements BaseRepository<E, ID> {

    private final EntityManager entityManager;

    public BaseRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public abstract Class<E> getEntityClass();

    @Override
    public E save(E element) {
        entityManager.persist(element);
        return element;
    }

    @Override
    public void delete(E element) {
        update(element);
    }


    @Override
    public E update(E element) {
        return entityManager.merge(element);
    }

    @Override
    public Optional<E> findById(ID id) {

        Optional<E> optional = Optional.empty();
        try {

            E data = entityManager.find(getEntityClass(), id);
            optional = Optional.of(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return optional;
    }

    @Override
    public boolean existById(ID id) {

        Optional<E> optional = findById(id);
        return optional.isPresent();
    }

    @Override
    public List<E> findAll() {

        return entityManager.createQuery("select e from " + getEntityClass().getSimpleName() + " " +
                "as e ", getEntityClass()).getResultList();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
