<?php

$query = htmlspecialchars($_GET['query']) . ", Hamilton, ON";

// Get place ID
$data = file_get_contents("https://maps.googleapis.com/maps/api/place/findplacefromtext/json?key=AIzaSyCQF-5J0JjiHZxGJrXGsGpiy9oULf3lbsE&inputtype=textquery&input=" . urlencode($query));
$data = json_decode($data, true);

$place_id = $data['candidates'][0]['place_id'];

// Now lets grab the info about that place
$placedata = file_get_contents("https://maps.googleapis.com/maps/api/place/details/json?key=AIzaSyCQF-5J0JjiHZxGJrXGsGpiy9oULf3lbsE&placeid=" . $place_id);
$placedata = json_decode($placedata, true)

?>

<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <link rel="stylesheet" href="./assets/app.css">

    <title>GeoHam</title>
  </head>
  <body>
    <div class="container">
        <div class="top">
            <img src="./assets/img/logo-black.png" />
        </div>
        <img class="header-img" src="<?php echo "https://maps.googleapis.com/maps/api/place/photo?photoreference=" . $placedata['result']['photos'][0]['photo_reference'] . "&key=AIzaSyCQF-5J0JjiHZxGJrXGsGpiy9oULf3lbsE&maxwidth=1000" ?>" />
        <h2><?php echo $placedata['result']['name'] ?></h2>

        <div class="address"><?php echo $placedata['result']['formatted_address'] ?></div>

        <div class="review">
            <img src="<?php echo $placedata['result']['reviews'][0]['profile_photo_url'] ?>" />
            <b><?php echo $placedata['result']['reviews'][0]['author_name'] ?> says:</b> <?php echo $placedata['result']['reviews'][0]['text'] ?>
        </div>

        <a class="btn btn-primary btn-lg btn-block" href="https://wego.here.com/directions/publicTransport/mylocation/firstOntario%20Arena"><i class="fas fa-bus"></i> Go via Public Transport</a>
        <a class="btn btn-primary btn-lg btn-block" href="https://wego.here.com/directions/walk/mylocation/firstOntario%20Arena"><i class="fas fa-walking"></i> Walk There</a>
        <br />
    </div>

    <!-- Op`t`ional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
  </body>
</html>