-- Insertar datos en customer
INSERT INTO customer (name, last_name, address, phone, email)
VALUES (
        'Alejandro',
        'Rua',
        'B° San Fco. m.228 l.12',
        '3886523168',
        'alejandrorua111@gmail.com'
    ),
    (
        'Marcos',
        'Perez',
        'B° 18 de nov. m.28 l.1',
        '3886987845',
        'marcos@gmail.com'
    ),
    (
        'Luis',
        'Ramirez',
        'B° San Fco. m.220 l.2',
        '3886569877',
        'luis_ramirez@gmail.com'
    ),
    (
        'Fabian',
        'Lucio',
        'B° 518 viviendas',
        '3886897456',
        'lucio@gmail.com'
    ),
    (
        'Dante',
        'Paz',
        'B° La loma m.233',
        '3886545232',
        'paz@gmail.com'
    ),
    (
        'Margarita',
        'Lopez',
        'B° San Fco. m.12 l.22',
        '3885659874',
        'margatira_lopez@gmail.com'
    ),
    (
        'Fabiana',
        'Alvarez',
        'B° 22 de mayo m.12 l.9',
        '3886784512',
        'alv_fab@gmail.com'
    ),
    (
        'Maria',
        'Perez',
        'B° Santa Rosa m.99 l.1',
        '3886456523',
        'perez_maria@gmail.com'
    ),
    (
        'Luisa',
        'Ramirez',
        'B° San Fco. m.18 l.12',
        '3886451263',
        'ramirez_luisa@gmail.com'
    );
-- Insertar datos en pizza
INSERT INTO pizza (name, price, description, available)
VALUES (
        'Muzzarella',
        6000.00,
        'salsa, queso muzzarella, oregano y aceituna',
        TRUE
    ),
    (
        'Jamon',
        7000.00,
        'salsa, jamon, queso muzzarella, oregano y aceituna',
        TRUE
    ),
    (
        'Napolitana',
        7000.00,
        'salsa, queso muzzarella, tomate, ajo, oregano y aceituna',
        TRUE
    ),
    (
        'Calabresa',
        7000.00,
        'salsa, queso muzzarella, calabresa, oregano y aceituna',
        TRUE
    ),
    (
        'Fugazzeta',
        7000.00,
        'queso muzzarella, cebolla, oregano y aceituna',
        TRUE
    ),
    (
        'Especial',
        9500.00,
        'salsa, queso muzzarella, huevo, jamon, tomate, morron, ajo, oregano y aceituna',
        TRUE
    );