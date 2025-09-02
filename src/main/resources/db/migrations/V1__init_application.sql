-- Members, Addresses, Positions (FKs ficam em tb_members)
CREATE TABLE tb_addresses (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              street VARCHAR(255),
                              number VARCHAR(50),
                              complement VARCHAR(255),
                              city VARCHAR(100),
                              state VARCHAR(100),
                              zip_code VARCHAR(20)
);

CREATE TABLE tb_positions (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              position VARCHAR(100) NOT NULL,
                              description VARCHAR(255)
);

CREATE TABLE tb_members (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(255) NOT NULL,
                            email VARCHAR(255) NOT NULL,
                            phone VARCHAR(20),
                            age INT,
                            date_of_birth DATE,
                            date_of_baptism DATE,

                            address_id BIGINT NOT NULL,
                            position_id BIGINT NOT NULL,

                            password VARCHAR(255) NOT NULL,
                            role VARCHAR(50) NOT NULL,

                            created_at DATE,
                            updated_at DATE,

                            CONSTRAINT uk_members_email UNIQUE (email),
                            CONSTRAINT fk_members_address FOREIGN KEY (address_id)
                                REFERENCES tb_addresses (id)
                                ON DELETE RESTRICT
                                ON UPDATE RESTRICT,
                            CONSTRAINT fk_members_position FOREIGN KEY (position_id)
                                REFERENCES tb_positions (id)
                                ON DELETE RESTRICT
                                ON UPDATE RESTRICT
);

-- (Opcional) Índices úteis
CREATE INDEX idx_members_role ON tb_members(role);
CREATE INDEX idx_members_position ON tb_members(position_id);
