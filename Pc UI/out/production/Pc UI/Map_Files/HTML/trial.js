function readFromFile() {
        jQuery.get('./result.txt', function(data) {
          var locArr = data.split(",")
          //optional stuff to do after success
        });