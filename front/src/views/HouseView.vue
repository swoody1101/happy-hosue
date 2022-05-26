<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h3 class="title"><b-icon icon="house-fill"></b-icon>거래정보 조회</h3>
    <table class="tb">
      <tr>
        <td class="aaaa search-bar">
          <house-search-bar></house-search-bar>
        </td>
        <td>
          <div style="height: 300px; width: 600px; background-color: red"></div>
        </td>
      </tr>
      <tr>
        <td class="aaaa">
          <house-list />
        </td>
        <td class="map">
          <house-map></house-map>
        </td>
      </tr>
    </table>
  </b-container>
</template>

<script>
import HouseSearchBar from "@/components/house/HouseSearchBar.vue";
import HouseMap from "@/components/house/HouseMap.vue";
import HouseList from "@/components/house/HouseList.vue";

export default {
  name: "HouseView",
  components: {
    HouseSearchBar,
    HouseMap,
    HouseList,
  },
};
</script>
<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Black+Han+Sans&display=swap");

.title {
  font-family: "Black Han Sans", sans-serif;
  font-size: 50px;
  text-align: left;
  margin: 40px;
}
* {
  margin: 0;
  padding: 0;
}

/* 전체 가운데 정렬 해주기 */
.container {
  margin: auto;
  width: 100%;
}

.tb {
  width: 100%;
}

.aaaa {
  width: 40%;
}

.map {
  margin-left: 100px;
}
</style>
