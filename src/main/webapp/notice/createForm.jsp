<%@ page contentType="text/html; charset=UTF-8" %> 
<jsp:include page="../header.jsp"></jsp:include>

<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title>notice/createForm.jsp</title> 
<style type="text/css"> 
*{ 
  font-family: BinggraeMelona; 
  font-size: 24px; 
} 
</style> 
<link href="../css/style.css" rel="stylesheet" type="text/css">
</head> 
<body>
   <div>
        <table style="width:100%;">
             <colgroup>
                <col width="15%">
                <col width="*%"/>
            </colgroup> 
            <tbody>
                <tr>
                    <th scope="row"><span></span>제목</th>
                    <td style="padding-left:10px;"><input type="text" id="noticesub" name="noticesub" style="width:790px;" value='${dto.noticesub}'></td>
                </tr>
                <tr>
                        <th scope="row"><span></span>내용</th>
                        <td style="padding-left:10px;">
                        <textarea rows="5" cols="100" title="내용" id="noticecon" name="noticecon"  style="width:790px; height:200px;" value='${dto.noticecon}'></textarea>
                        </td>
                </tr>
                <tr>
                        <th scope="row">파일첨부</th>
                        <td style="padding-left:10px;">
                            
                            <div id="fileInfo" ></div>
                            <input type="file" id="attachFile" name="attachFile" style="display:none">
                            <input type="hidden" id="attFileNm" name="attFileNm" >
                            <!-- <input type="text" id="fileListLength" name="fileListLength"> -->
                            <div align="left">
                            <input type="button" id="btn_file"  style="text-align: center; width:120px;" value="파일찾기"/>
                            <input type="button" id="btn_del_file"  style="text-align: center; width:120px;" value="파일삭제"/>
                            </div>
                        </td>
                        
                </tr>
            </tbody>
        </table>
        </div>
        <div style="text-align: center; padding: 20px;">
            <input type="button" id="btn_reg"  style="text-align: center; width:100px;" value="저장"  />
            <input type="button" id="btn_list" style="text-align: center; width:100px;" value="목록"  />
       </div>
</FORM>
</body> 
</html> 
