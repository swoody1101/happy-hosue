<template>
  <div class="search-bar-container">
    <div>
      <b-form-select
        v-model="sidoName"
        :options="sidos"
        @change="gugunList"
        class="select-item"
      ></b-form-select>
      <b-form-select
        v-model="gugunName"
        :options="guguns"
        @change="dongList"
        class="select-item"
      ></b-form-select>
      <b-form-select
        v-model="dongName"
        :options="dongs"
        class="select-item"
      ></b-form-select>
    </div>
    <form @submit="searchApt">
      <input
        type="text"
        v-model="keyword"
        placeholder="아파트 이름을 입력하세요."
        class="search-apt-name"
      />
      <button type="submit" class="search-apt-btn">검색</button>
    </form>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseSearchBar",
  data() {
    return {
      sidoName: null,
      gugunName: null,
      dongName: null,
      keyword: "",
    };
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "dongs", "houses"]),
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions(houseStore, [
      "getSido",
      "getGugun",
      "getDong",
      "getHouseList",
      "getDealList",
    ]),
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
    ]),
    gugunList() {
      // console.log("sido", this.sidoName);
      this.CLEAR_GUGUN_LIST();
      this.gugunName = null;
      if (this.sidoName) this.getGugun(this.sidoName);
    },
    dongList() {
      // console.log("gu", this.gugunName);
      this.CLEAR_DONG_LIST();
      this.dongName = null;
      let params = {
        siguName: this.sidoName,
        gugunName: this.gugunName,
      };
      if (this.gugunName) this.getDong(params);
    },
    searchApt(event) {
      event.preventDefault();
      let params = {
        siguName: this.sidoName,
        gugunName: this.gugunName,
        dongName: this.dongName,
        keyword: this.keyword,
      };
      if (this.dongName) this.getDealList(params);
    },
  },
};
</script>

<style>
.select-box {
  width: 10px;
}
.select-item {
  display: inline-block;
  width: 30%;
  margin: 5px;
}

.search-apt-name {
  padding: 10px;
  margin: 10px;
  border: 1px solid rgb(179, 179, 179);
  width: 60%;
}

.search-apt-btn {
  padding: 10px;

  border: 1px solid rgb(179, 179, 179);
  background-color: rgb(115, 115, 115);
  color: whitesmoke;
}
</style>
