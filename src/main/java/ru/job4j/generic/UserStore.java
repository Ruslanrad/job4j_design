package ru.job4j.generic;

public class UserStore implements Store<User> {

    private final Store<User> store = new MemStore<>();

    @Override
    public void add(User model) {
        if (store.findById(model.getId()) == null) {
            store.add(model);
        }
    }

    @Override
    public boolean replace(String id, User model) {
        return store.replace(id, model);
    }

    @Override
    public void delete(String id) {
        store.delete(id);
    }

    @Override
    public User findById(String id) {
        return store.findById(id);
    }
}