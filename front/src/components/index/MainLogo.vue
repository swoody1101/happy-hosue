<template>
  <div class="container">
    <div class="carousel-container">
      <div class="black-box"></div>
      <div class="logo-container">
        <img src="@/assets/logo02.png" />
      </div>
      <div class="carousel">
        <b-carousel id="carousel-fade" fade indicators :interval="8000">
          <b-carousel-slide
            img-src="https://cdn.pixabay.com/photo/2020/08/25/18/28/workplace-5517744_960_720.jpg"
          ></b-carousel-slide>
          <b-carousel-slide
            img-src="https://cdn.pixabay.com/photo/2019/03/11/09/58/ikea-4048225_960_720.jpg"
          ></b-carousel-slide>
          <b-carousel-slide
            img-src="https://cdn.pixabay.com/photo/2021/10/06/15/05/bedroom-6686061_960_720.jpg"
          ></b-carousel-slide>
          <b-carousel-slide
            img-src="https://cdn.pixabay.com/photo/2017/08/06/11/07/interior-2591355_960_720.jpg"
          ></b-carousel-slide>
          <b-carousel-slide
            img-src="https://cdn.pixabay.com/photo/2016/10/16/10/29/office-space-1744801_960_720.jpg"
          ></b-carousel-slide>
        </b-carousel>
      </div>
    </div>
  </div>
</template>

<script>
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
export default {
  name: "MainLogo",
};
</script>

<style scoped>
/* .container {
  max-width: 1400px;
  margin: auto;
} */

/* 이미지 돌아가는 부분 */
.carousel-container {
  /* min-width: 100%; */
  /* max-width: 1400px; */
  width: 100%;
  position: relative;
}

.carousel {
  width: 100%;
  /* position: absolute; */
  z-index: 100;
}

.black-box {
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.652);
  z-index: 101;
  position: absolute;
}

.logo-container {
  animation: fadein 1.5s;
  position: absolute;
  z-index: 102;
}

@keyframes fadein {
  from {
    opacity: 0;
    transform: translate3d(0, 10px, 0);
  }
  to {
    opacity: 1;
    transform: translateZ(0);
  }
}
</style>
