<?php

    header('Content-Type: application/json');

    $category = htmlspecialchars($_GET['category']);

    $config = [];
    $config['DB_HOST'] = "localhost";
    $config['DB_DATABASE'] = "geoham";
    $config['DB_USER'] = "user";
    $config['DB_PASSWORD'] = "pass";


    $dsn = "mysql:host=" . $config['DB_HOST'] . ";dbname=" . $config['DB_DATABASE'] . ";charset=utf8mb4";
    $opt = [
        PDO::ATTR_ERRMODE            => PDO::ERRMODE_EXCEPTION,
        PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,
        PDO::ATTR_EMULATE_PREPARES   => false,
    ];
    $pdo = new PDO($dsn, $config['DB_USER'], $config['DB_PASSWORD'], $opt);
    $sql = "SELECT * FROM locations WHERE category = ?";
    $stmt = $pdo->prepare($sql);
    $stmt->execute([$category]);

    $data = [];
    $i = 0;

    foreach($stmt as $row) {
        $data[] = array(
            "name" => $row['name'],
            "x" => $row['x'],
            "y" => $row["y"]
        );

        $i++;
    }

    echo json_encode($data);
