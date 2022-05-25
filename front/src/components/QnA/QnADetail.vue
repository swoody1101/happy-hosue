<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글보기</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="listArticle">목록</b-button>
      </b-col>
      <b-col class="text-right">
        <b-button
          variant="outline-info"
          size="sm"
          @click="moveModifyArticle"
          class="mr-2"
          v-if="qna.writer == userInfo.userid"
          >글수정</b-button
        >
        <b-button
          variant="outline-danger"
          size="sm"
          @click="deleteArticle"
          v-if="qna.writer == userInfo.userid || userInfo.role == 'ROLE_ADMIN'"
          >글삭제</b-button
        >
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>${qna.qna_no}.${qna.title} 
          [${qna.writer}]</h3><div><h6>${qna.content}</div><div></h6></div>`"
          class="mb-2"
          border-variant="dark"
          no-body
        >
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
    <b-button @click="moveRegistReply">답글달기</b-button>
  </b-container>
</template>

<script>
import { apiInstance } from "@/api/index.js";
import { mapState } from "vuex";

const api = apiInstance();
const memberStore = "memberStore";

export default {
  name: "qnaDetail",
  data() {
    return {
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
  created() {
    api.get(`/qna/detail/${this.$route.params.qna_no}`).then(({ data }) => {
      this.qna = data;
    });
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

<style></style>
