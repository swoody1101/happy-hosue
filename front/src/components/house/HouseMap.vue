<template>
  <div>
    <div id="map"></div>
    <button @click="addMarker">불러와</button>
  </div>
</template>

<script>
import { mapState } from "vuex";
const houseStore = "houseStore";

export default {
  data() {
    return {
      map: null,
      markers: [],
      geocoder: null,
    };
  },
  computed: {
    ...mapState(houseStore, ["houses", "house", "sido", "gugun", "dong"]),
  },
  created() {
    console.log("maap created");
    setTimeout(() => {
      this.initMap();
    }, 500);
  },
  watch: {
    houses() {
      this.addMarker();
    },
    house() {
      this.detailMarker();
    },
  },
  mounted() {
    /* global kakao */
    console.log("map mounted");
    if (window.kakao && window.kakao.maps) {
      console.log("exists");
      this.initMap();
    } else {
      const script = document.createElement("script");
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=a4fdf6bad8dd4101f7624f1f9446e8ab&libraries=services";
      document.head.appendChild(script);
      script.onload = () => {
        kakao.maps.load(this.initMap);
        // console.log("loaded");
      };
    }
  },
  methods: {
    initMap() {
      console.log("initMap 호출");
      var container = document.getElementById("map");
      var options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5,
      };

      this.map = new kakao.maps.Map(container, options);

      this.geocoder = new kakao.maps.services.Geocoder();
      var marker = new kakao.maps.Marker({
        position: this.map.getCenter(),
      });
      marker.setMap(this.map);
    },
    addMarker() {
      console.log("addMarker 호출");

      // var geocoder = new kakao.maps.services.Geocoder();
      let _this = this;
      console.log("geocoder", _this.geocoder);
      if (this.houses.length == 0) {
        var address = this.sido + " " + this.gugun + " " + this.dong;
        console.log("목록이 없을 때 ", address);

        _this.geocoder.addressSearch(address, function (result, status) {
          if (status === kakao.maps.services.Status.OK) {
            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
            var marker = new kakao.maps.Marker({
              map: _this.map,
              position: coords,
            });
            var infowindow = new kakao.maps.InfoWindow({
              content:
                '<div style="width:150px;text-align:center;padding:6px 0;">해당 지역에 거래 내역이 없습니다.</div>',
            });
            infowindow.open(_this.map, marker); // 지도의 중심을 이동
            marker.setMap(_this.map);
            _this.map.setCenter(coords);
          }
        });
      }
      this.houses.forEach((house) => {
        var houseAddress = house.address;
        console.log(house.address);
        console.log(houseAddress);

        _this.geocoder.addressSearch(houseAddress, function (result, status) {
          if (status === kakao.maps.services.Status.OK) {
            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
            var marker = new kakao.maps.Marker({
              map: _this.map,
              position: coords,
              title: house.aptName,
            }); // 인포윈도우로 장소에 대한 설명을 표시합니다
            var infowindow = new kakao.maps.InfoWindow({
              content:
                '<div style="width:150px;text-align:center;padding:6px 0;">' +
                house.aptName +
                "</div>",
            });
            infowindow.open(_this.map, marker); // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
            marker.setMap(_this.map);
            _this.map.setCenter(coords);
          }
        });
      });
    },
    detailMarker() {
      console.log("addMarker 호출");
      let _this = this;
      _this.geocoder.addressSearch(
        _this.house.address,
        function (result, status) {
          if (status === kakao.maps.services.Status.OK) {
            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
            var marker = new kakao.maps.Marker({
              map: _this.map,
              position: coords,
              title: _this.house.aptName,
            });
            var infowindow = new kakao.maps.InfoWindow({
              content:
                '<div style="width:150px;text-align:center;padding:6px 0; bold">' +
                _this.house.address +
                " " +
                _this.house.aptName +
                "</div>",
            });
            infowindow.open(_this.map, marker); // 지도의 중심을 이동
            marker.setMap(_this.map);
            _this.map.setCenter(coords);
          }
        },
      );
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
