<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <h5 class="title">상세보기</h5>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-right"> </b-col>
    </b-row>
    <div v-for="(q, i) in qnas" :key="i">
      <b-row class="mb-1">
        <b-col class="qna-container">
          <h2 class="qna-title">
            {{ q.qna_no }}.{{ q.title }}[{{ q.writer }}]
          </h2>
          <div class="qna-content">
            {{ q.content }}
          </div>
        </b-col>
        <div
          v-if="userInfo.role == 'ROLE_ADMIN' || qna.writer == userInfo.userid"
          class="btn-container"
        >
          <div>
            <b-button
              variant="outline-info"
              size="sm"
              @click="moveModifyArticle"
              class="mr-2 btns"
              >글수정</b-button
            >
          </div>
          <div>
            <b-button
              class="btns"
              variant="outline-danger"
              size="sm"
              @click="deleteArticle"
              >글삭제</b-button
            >
          </div>
          <div>
            <b-button class="btns" @click="moveRegistReply">답글달기</b-button>
          </div>
        </div>
      </b-row>
    </div>
    <b-col class="text-left">
      <b-button variant="outline-primary" @click="listArticle">글목록</b-button>
    </b-col>
  </b-container>
</template>

<script>
import { mapState } from "vuex";

import { selectOne, selectGroup, getGroupNo } from "@/api/qna.js";

const memberStore = "memberStore";

export default {
  name: "qnaDetail",
  data() {
    return {
      qnas: [],
      qna: {},
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    message() {
      if (this.qna.content) return this.qna.content.split("\n").join("<br>");
      return "";
    },
  },
  mounted() {
    selectOne(
      this.$route.params.qna_no,
      (data) => {
        this.qna = data.data;
        console.log("qna", data);

        getGroupNo(this.$route.params.qna_no, (data) => {
          this.group_no = data.data;
          console.log(this.group_no);

          selectGroup(this.group_no, (data) => {
            this.qnas = data.data;
            console.log(this.qnas);
          });
        });
      },
      (error) => {
        console.log("qnaerror");
        console.log(error);
      },
    );
  },
  methods: {
    listArticle() {
      this.$router.push({ name: "qnaList" });
    },
    moveModifyArticle() {
      this.$router.replace({
        name: "qnaModify",
        params: { qna_no: this.qna.qna_no },
      });
    },
    deleteArticle() {
      if (confirm("삭제하시겠습니까?")) {
        this.$router.replace({
          name: "qnaDelete",
          params: { qna_no: this.qna.qna_no },
        });
      }
    },
    moveRegistReply() {
      console.log(this.qna.qna_no);
      this.$router.replace({
        name: "qnaReply",
        params: { qna_no: this.qna.qna_no },
      });
    },
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap");
.title {
  margin-left: 20px;
  text-align: left;
  font-family: "Noto Sans KR", sans-serif;
  font-size: 40px;
}
.qna-container {
  border: 1px solid rgb(129, 129, 129);
  padding: 20px;
  border-radius: 3px;
}
.qna-title {
  font-size: 35px;
  font-weight: bolder;
}
.qna-content {
  font-size: 25px;
}
.btn-container b-button {
  display: block;
}
.btns {
  font-size: 20px;
  width: 100%;
  border-radius: 0;
}
</style>
