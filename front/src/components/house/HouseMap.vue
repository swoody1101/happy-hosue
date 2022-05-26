<template>
  <section class="test" v-if="houses && houses.length != 0">
    <div id="map"></div>
  </section>
</template>

<script>
import { mapState } from "vuex";
const houseStore = "houseStore";

export default {
  name: "HouseMap",
  data() {
    return {
      container: Document,
      map: null,
      markers: [],
      latitude: 0,
      longitude: 0,
    };
  },
  computed: {
    ...mapState(houseStore, ["houses", "sido", "gugun", "dong"]),
  },
  mounted() {
    if (this.houses.length) console.log(this.houses.length);
    /* global kakao */
    window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
  },
  updated() {
    this.addMarker();
  },
  methods: {
    initMap() {
      console.log("Map init");
      this.container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5,
      };
      let map = new kakao.maps.Map(this.container, options);
      var marker = new kakao.maps.Marker({
        position: map.getCenter(),
      });
      marker.setMap(map);
    },
    addScript() {
      console.log("add Script");
      const script = document.createElement("script");
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=a4fdf6bad8dd4101f7624f1f9446e8ab";
      document.head.appendChild(script);
    },
    addMarker() {
      var mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 4,
      };
      var map = new kakao.maps.Map(this.container, mapOption); // 주소-좌표 변환 객체
      var geocoder = new kakao.maps.services.Geocoder(); // 주소로 좌표를 검색
      var imageSrc =
        "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

      if (this.houses == null) {
        var address = this.sido + " " + this.gugun + " " + this.dong;
        console.log(address);
        geocoder.addressSearch(address, function (result, status) {
          if (status === kakao.maps.services.Status.OK) {
            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
            var marker = new kakao.maps.Marker({
              map: map,
              position: coords,
            });
            var infowindow = new kakao.maps.InfoWindow({
              // 장소 설명
              content:
                '<div style="width:150px;text-align:center;padding:6px 0;">' +
                "해당 도시에 거래 내역이 없습니다." +
                "</div>",
            });
            infowindow.open(map, marker); // 지도의 중심을 이동
            marker.setMap(map);
            map.setCenter(coords);
          }
        });
      }

      this.houses.forEach((element) => {
        var imageSize = new kakao.maps.Size(24, 35);
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
        var houseAddress = element.address;

        geocoder.addressSearch(houseAddress, function (result, status) {
          // 정상적으로 검색이 완료됐으면
          if (status === kakao.maps.services.Status.OK) {
            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
            var marker = new kakao.maps.Marker({
              map: map,
              position: coords,
              image: markerImage, // 마커 이미지
              title: element.aptName,
            }); // 인포윈도우로 장소에 대한 설명을 표시합니다
            var infowindow = new kakao.maps.InfoWindow({
              content:
                '<div style="width:150px;text-align:center;padding:6px 0;">' +
                houseAddress +
                " " +
                element.aptName +
                "</div>",
            });
            infowindow.open(map, marker); // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
            marker.setMap(map);
            map.setCenter(coords);
          }
        });
      });
    },
  },
};
</script>

<style scoped>
.test {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

#map {
  width: 400px;
  height: 500px;
  border: 1px #a8a8a8 solid;
}
</style>
