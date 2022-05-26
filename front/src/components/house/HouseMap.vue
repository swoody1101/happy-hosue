<template>
  <div>
    <div id="map"></div>
  </div>
</template>

<script>
import { mapState } from "vuex";
const houseStore = "houseStore";

export default {
  data() {
    return {};
  },
  computed: {
    ...mapState(houseStore, ["houses", "sido", "gugun", "dong"]),
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=a4fdf6bad8dd4101f7624f1f9446e8ab&libraries=service";
      document.head.appendChild(script);
      this.addMarker();
    }
  },
  updated() {
    console.log("updated 호출");
  },
  methods: {
    initMap() {
      console.log("initMap 호출");
      var container = document.getElementById("map");
      var options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 3,
      };

      var map = new kakao.maps.Map(container, options);
      map.setMapTypeId(kakao.maps.MapTypeId.HYBRID);

      var marker = new kakao.maps.Marker({
        position: map.getCenter(),
      });
      marker.setMap(map);
      this.addMarker();
    },
    addMarker() {
      console.log("addMarker 호출");

      var container = document.getElementById("map");
      var mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 4,
      };
      var map = new kakao.maps.Map(container, mapOption);
      var geocoder = new kakao.maps.services.Geocoder();

      if (this.houses.length == 0) {
        var address = this.sido + " " + this.gugun + " " + this.dong;
        console.log("목록이 없을 때 ", address);

        geocoder.addressSearch(address, function (result, status) {
          if (status === kakao.maps.services.Status.OK) {
            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
            var marker = new kakao.maps.Marker({
              map: map,
              position: coords,
            });
            var infowindow = new kakao.maps.InfoWindow({
              content:
                '<div style="width:150px;text-align:center;padding:6px 0;">해당 지역에 거래 내역이 없습니다.</div>',
            });
            infowindow.open(map, marker); // 지도의 중심을 이동
            marker.setMap(map);
            map.setCenter(coords);
          }
        });
      }
      this.houses.forEach((house) => {
        var houseAddress = house.address;
        console.log(house.address);
        console.log(houseAddress);

        geocoder.addressSearch(houseAddress, function (result, status) {
          if (status === kakao.maps.services.Status.OK) {
            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
            var marker = new kakao.maps.Marker({
              map: map,
              position: coords,
              title: house.aptName,
            }); // 인포윈도우로 장소에 대한 설명을 표시합니다
            var infowindow = new kakao.maps.InfoWindow({
              content:
                '<div style="width:150px;text-align:center;padding:6px 0;">' +
                houseAddress +
                " " +
                house.aptName +
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

<style>
#map {
  width: 400px;
  height: 300px;
}
</style>
