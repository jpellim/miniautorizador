package br.com.vr.miniautorizador.domain.use_case;


import org.testcontainers.containers.MySQLContainer;

public class MysqlContainer extends MySQLContainer<MysqlContainer> {

    private static final String IMAGE_VERSION = "mysql:latest";
    private static MysqlContainer container;

    private MysqlContainer() {
        super(IMAGE_VERSION);
    }

    public static MysqlContainer getInstance() {
        if (container == null) {
            container = new MysqlContainer();
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("DB_URL", container.getJdbcUrl());
        System.setProperty("DB_USERNAME", container.getUsername());
        System.setProperty("DB_PASSWORD", container.getPassword());
    }

    @Override
    public void stop() {
        //do nothing, JVM handles shut down
    }

}
