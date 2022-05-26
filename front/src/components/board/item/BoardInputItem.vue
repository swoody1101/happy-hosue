<template>
  <div>
    <h2 class="title">게시글 작성</h2>

    <form @submit="onSubmit" @reset="onReset">
      <div v-if="userInfo.role == 'ROLE_ADMIN'" class="notice-check">
        <input type="checkbox" v-model="article.bnotice" checked />공지사항
      </div>
      <table class="write-table">
        <tr>
          <td class="write-label">
            <label for="writer">작성자</label>
          </td>
          <td class="write-input">
            <input
              type="text"
              id="writer"
              v-model="article.bwriter"
              required
              disabled
            />
          </td>
        </tr>
        <tr>
          <td class="write-label">
            <label for="title">제목</label>
          </td>
          <td class="write-input">
            <input
              type="text"
              id="title"
              v-model="article.btitle"
              required
              placeholder="제목 입력"
            />
          </td>
        </tr>

        <tr>
          <td></td>
          <td class="write-input">
            <textarea
              name=""
              id="content"
              cols="50"
              rows="10"
              max-rows="15"
              v-model="article.bcontent"
              placeholder="내용 입력"
            ></textarea>
          </td>
        </tr>
      </table>

      <button
        type="submit"
        variant="primary"
        class="m-1 write-btn"
        v-if="this.type === 'register'"
      >
        글작성
      </button>
      <button type="submit" variant="primary" class="m-1 write-btn" v-else>
        글수정
      </button>
      <button type="reset" variant="danger" class="m-1 clear-btn">
        초기화
      </button>
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
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap");

label {
  font-weight: bold;
}

td {
  padding: 5px;
}

input,
textarea {
  border-radius: 3px;
  padding: 7px;
}

.title {
  margin-left: 50px;
  text-align: left;
  font-family: "Noto Sans KR", sans-serif;
}

.write-table {
  width: 100%;
}

.write-table tr {
  width: 100%;
}

.write-label {
  width: 10%;
}

.write-input {
  width: 90%;
}

.write-input {
  text-align: left;
}
.write-input input,
.write-input textarea {
  width: 80%;
}

.notice-check {
  margin-top: 20px;
  padding-left: 120px;
  text-align: left;
}

.write-btn {
  padding: 15px 25px;
  border: none;
  color: whitesmoke;
  background-color: rgb(138, 138, 138);
  border-radius: 5px;
  font-weight: bolder;
}

.clear-btn {
  padding: 13px 23px;
  border: 1px solid rgb(138, 138, 138);
  color: rgb(138, 138, 138);
  background-color: whitesmoke;
  border-radius: 5px;
  font-weight: bolder;
}
</style>
