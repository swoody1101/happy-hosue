<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset">
        <b-form-group
          id="userid-group"
          label="작성자:"
          label-for="userid"
          description="작성자를 입력하세요."
        >
          <b-form-input
            id="userid"
            disabled
            v-model="qna.writer"
            type="text"
            required
            placeholder="작성자 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group id="content-group" label="내용:" label-for="content">
          <b-form-textarea
            id="content"
            v-model="qna.content"
            placeholder="내용 입력..."
            rows="10"
            max-rows="15"
          ></b-form-textarea>
        </b-form-group>

        <b-button type="submit" variant="primary" class="m-1">글작성</b-button>
        <b-button type="reset" variant="danger" class="m-1">초기화</b-button>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
import { apiInstance } from "@/api/index.js";
const api = apiInstance();
export default {
  name: "QnaReplyItem",
  data() {
    return {
      qna: {
        qna_no: 0,
        group_no: 0,
        group_order: 0,
        depth: 0,
        title: "",
        writer: this.$store.state.memberStore.userInfo.userid,
        content: "",
      },
      isUserid: false,
    };
  },
  created() {
    api.get(`/qna/detail/${this.$route.params.qna_no}`).then(({ data }) => {
      console.log("data", data);
      this.qna = data;
      this.qna.writer = "admin";
      this.qna.content = null;
    });
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.qna.writer &&
        ((msg = "작성자 입력해주세요"),
        (err = false),
        this.$refs.userid.focus());
      err &&
        !this.qna.content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

      if (!err) alert(msg);
      else this.type = this.registReply();
    },
    onReset(event) {
      event.preventDefault();
      this.qna.title = "";
      this.qna.content = "";
      this.qna.writer = "";
      this.$router.push({ name: "qnaList" });
    },
    registReply() {
      console.log("qna_no", this.qna.qna_no);
      api
        .post(`/qna/writereply/${this.qna.qna_no}`, {
          qna_no: this.qna.qna_no,
          content: this.qna.content,
          writer: this.qna.writer,
        })
        .then(({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        });
    },
    moveList() {
      this.$router.push({ name: "qnaList" });
    },
  },
};
</script>

<style></style>
