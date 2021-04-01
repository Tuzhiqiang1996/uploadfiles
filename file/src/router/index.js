import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: "/",
      name: "home",
      component: () => import(/* webpackChunkName: "home" */ "@/view/index.vue")
    },
    {
      path: "/HelloWorld",
      name: "HelloWorld",
      component: () =>
        import(/* webpackChunkName: "HelloWorld" */ "@/components/HelloWorld")
    },
    {
      path: "/xlvue",
      name: "xlvue",
      component: () =>
        import(/* webpackChunkName: "xlvue" */ "@/components/xlvue")
    },
    {
      path: "/xlsvue",
      name: "xlsvue",
      component: () =>
        import(/* webpackChunkName: "xlvue" */ "@/components/xlsvue")
    }
  ]
});
