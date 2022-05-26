<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h3 class="title"><b-icon icon="house-fill"></b-icon>거래정보 조회</h3>
    <b-row>
      <b-col class="left-container">
        <house-search-bar></house-search-bar>
      </b-col>
      <b-col></b-col>
    </b-row>
    <b-row>
      <b-col class="left-container" cols="6" align="left">
        <house-list />
      </b-col>
      <b-col cols="6">
        <house-map></house-map>
      </b-col>
    </b-row>
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
.left-container {
  width: 30%;
}
</style>
