/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
    
      
      var images = ["images/image 0.jpg", "images/image 1.jpg", "images/image 2.jpg", "images/image 3.jpg"];
      var captions = ["what", "hey", "ey", "yooo"];
      
    
      var currentSlide = 0;
      var isPlaying = true;
      var slidePicture = document.getElementById("picture");
      var slideCaption = document.getElementById("caption");
      var pausePlay = document.getElementById("pause");
      var timer;
      
      
    
      
      timer = setInterval("next()", 4000);
     
     

       function next() {
        //slideCaption.innerHTML = "i hate javascript";
        
          if (currentSlide !== images.length) {
              currentSlide++;
              slidePicture.setAttribute("src", images[currentSlide]);
              slideCaption.innerHTML = captions[currentSlide];
          } else if (currentSlide === images.length) {
              currentSlide = 0;
              slidePicture.setAttribute("src", images[currentSlide]);
              slideCaption.innerHTML = captions[currentSlide];
          } else {
              //do nothing
            
          }
          
      };

      
      function prev() {
          if (currentSlide !== 0) {
              currentSlide--;
              slidePicture.setAttribute("src", images[currentSlide]);
              slideCaption.innerHTML = captions[currentSlide];
          }

    
      }; 


      function pause() {
        var pausePlay = document.getElementById("playPause");
        if (isPlaying) {
          pausePlay.src = "../../../images/icons/play.png"; 
          isPlaying = false;
          clearInterval(timer);
        } else {
          isPlaying = true;
          pausePlay.setAttribute("src", "../../../images/icons/pause.png");
          timer = setInterval("next()", 4000);
        }
      };
       
    
        
    

      
      
     
      
      
      
     