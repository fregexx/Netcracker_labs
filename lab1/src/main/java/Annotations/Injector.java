package Annotations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repositories.Repository;
import sorters.ISorter;
import utilities.Configurator;

import java.lang.reflect.Field;

public class Injector {

    private static final Logger LOGGER = LogManager.getLogger(Injector.class);

    public <T extends Repository> T inject(T repository) {
        Class<?> clazz = repository.getClass();
        Field[] fields = clazz.getSuperclass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Inject.class)) {
                ISorter sorter = Configurator.getInstance().getSorter();
                field.setAccessible(true);
                try {
                    field.set(repository, sorter);
                    LOGGER.info("Field has been injected: " + field.getName() + " -> " + sorter.getClass().getSimpleName());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return repository;
    }
}
