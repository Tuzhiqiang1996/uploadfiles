
let flie = "B05-B_WTW-SNL-05_10_20210329003_id.csv";
let d="B02-F_WTW-SNL-02_170_20210329003"
let file1 = flie.split(".");
let file2 = file1[0].split("_");
let num=file2[2]
 	//26	B02-F_WTW-SNL-02_170_20210329003	RE705S	LT01	RE705S-V1.3.2-20201022		1.3.2	0	酷宅云	170
 	//27	B05-B_WTW-SNL-05_10_20210329003	RE705F	LT02	RE705F-V1.3.5-20201218		1.3.5	0	酷宅云	10
let file3 = file2[0] + "_" + file2[1] + "_" + file2[2];
let params={

}
if(file2[0] =="B05-B"){
 params.orderNumber=file3
params.projectNumber="RE705S"
}else if(file2[0] =="B02-F"){

}
console.log(file2);

