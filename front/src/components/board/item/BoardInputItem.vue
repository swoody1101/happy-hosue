<template>
  <div>
    <h3>게시물 등록</h3>
    <form @submit="onSubmit" @reset="onReset">
      <div class="">
        <label for="writer">작성자</label>
        <input
          type="text"
          id="writer"
          v-model="article.bwriter"
          required
          disabled
        />
      </div>
      <div class="">
        <label for="title">제목</label>
        <input type="text" id="title" v-model="article.btitle" required />
      </div>

      <div v-if="userInfo.role == 'ROLE_ADMIN'">
        <input type="checkbox" v-model="article.bnotice" checked />공지사항
      </div>

      <div class="">
        <textarea
          name=""
          id="content"
          cols="50"
          rows="10"
          max-rows="15"
          v-model="article.bcontent"
          placeholder="내용 입력"
        ></textarea>
      </div>

      <button
        type="submit"
        variant="primary"
        class="m-1"
        v-if="this.type === 'register'"
      >
        글작성
      </button>
      <button type="submit" variant="primary" class="m-1" v-else>글수정</button>
      <button type="reset" variant="danger" class="m-1">초기화</button>
    </form>
  </div>
</template>

<script>
import { writeArticle, getArticle, modifyArticle } from "@/api/board";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "BoardInputItem",
  data() {
    return {
      // admin: false,
      article: {
        bno: 1,
        btitle: "",
        bwriter: "",
        bcontent: "",
        breadCount: "",
        bnotice: false,
      },
    };
  },
  props: {
    type: { type: String },
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    if (this.type == "modify") {
      console.log("야호", this.$route.params.articleno);
      this.article.bno = this.$route.params.articleno;
      getArticle(
        this.article.bno,
        (response) => {
          this.article = response.data;
        },
        (error) => {
          console.log(error);
        },
      );
    } else {
      this.article.bwriter = this.userInfo.userid;
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.article.bwriter &&
        ((msg = "작성자 입력해주세요"),
        (err = false),
        this.$refs.bwriter.focus());
      err &&
        !this.article.btitle &&
        ((msg = "제목 입력해주세요"), (err = false), this.$refs.btitle.focus());
      err &&
        !this.article.bcontent &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.bcontent.focus());

      if (!err) alert(msg);
      else
        this.type === "register" ? this.registArticle() : this.modifyArticle();
    },
    onReset(event) {
      event.preventDefault();
      this.article.bno = 0;
      this.article.btitle = "";
      this.article.bcontent = "";
      this.$router.push({ name: "boardList" });
    },
    registArticle() {
      writeArticle(
        this.article,
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        },
        (error) => {
          console.log(error);
        },
      );
    },
    modifyArticle() {
      modifyArticle(
        this.article,
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        },
        (error) => {
          console.log(error);
        },
      );
    },
    moveList() {
      this.$router.push({ name: "boardList" });
    },
    // confirmRule() {
    //   if (this.userInfo.role == "ROLE_ADMIN") {
    //     this.admin = true;
    //   } else {
    //     this.admin = false;
    //   }
    // },
  },
};
</script>

<style></style>
