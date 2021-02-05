CREATE TABLE device_specs (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    brand VARCHAR(50),
    model VARCHAR(50) NOT NULL,
    wifi_built_in BOOLEAN NOT NULL,
    bluetooth_built_in BOOLEAN NOT NULL
);

CREATE TABLE devices (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    id_room INT NOT NULL,
    is_indispensable BOOLEAN NOT NULL,
    id_specs BIGINT REFERENCES device_specs (id),
    UNIQUE(id_specs)
);

INSERT INTO devices(name, id_room, is_indispensable) VALUES ('Tele Mario', 1, true);
INSERT INTO devices(name, id_room, is_indispensable) VALUES ('Tele Roberto', 2, true);

INSERT INTO device_specs(brand, model, wifi_built_in, bluetooth_built_in) VALUES ('SONY', 'A1600', true, false);
INSERT INTO device_specs(brand, model, wifi_built_in, bluetooth_built_in) VALUES ('LG', 'X450A', true, false);