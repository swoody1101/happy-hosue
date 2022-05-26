<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h3 class="title"><b-icon icon="house-fill"></b-icon>거래정보 조회</h3>
    <b-row>
      <b-col>
        <house-search-bar></house-search-bar>
      </b-col>
    </b-row>
    <b-row>
      <b-col cols="6" align="left">
        <house-list />
      </b-col>
      <b-col cols="6">
        <house-detail />
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <house-map></house-map>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import HouseSearchBar from "@/components/house/HouseSearchBar.vue";
import HouseMap from "@/components/house/HouseMap.vue";
import HouseList from "@/components/house/HouseList.vue";
import HouseDetail from "@/components/house/HouseDetail.vue";

export default {
  name: "HouseView",
  components: {
    HouseSearchBar,
    HouseMap,
    HouseList,
    HouseDetail,
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
</style>
