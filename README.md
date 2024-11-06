<p align="center">
<img src="https://cdn.imweb.me/thumbnail/20231120/9b1551ea109cf.png" width="40%" height="30%" title="px(픽셀) 크기 설정" alt="Calculator"></img>
</p>

# 📌 Schdule Management Application

>- ## ⚙ 구현 기능
>1. 일정 생성 및 등록
>2. 전체 일정 조회
>3. 선택 일정 조회
>4. 선택 일정 수정
>5. 선택 일정 삭제
>---
>- ## 👷‍♂️ API 명세서
> <table style="border: 2px;" align="center">
  <tr>
    <td> 기능</td>
    <td> Method </td>
    <td> URI </td>
    <td> Request </td>
    <td> Responese </td>
    <td> StatusCode </td>
  </tr><tr>
    <td > 일정 생성 및 등록 </td>
    <td > POST </td>
    <td > /api/schedules </td>
    <td > 
      {<br>
        "user_id" : user_id;,<br>
        "password" : password;,<br>
        "detail" : 'detail';
      <br>}
    </td>
    <td > 
      {<br>
        "id": schedule_id, <br>
        "user_id" : user_id,<br>
        "detail" : 'detail';<br>
      }
    </td>
    <td > 
      2xx : 성공<br> 
      4xx : 실패<br> 
    </td>
  </tr><tr>
    <td > 전체 일정 조회 </td>
    <td > GET </td>
    <td > /api/schedules </td>
    <td > - </td>
    <td > 전체 응답 정보 </td>
    <td > 
      2xx : 성공<br> 
      4xx : 실패<br>
    </td>
  </tr><tr>
    <td > 선택 일정 조회 </td>
    <td > GET </td>
    <td > /api/schedules/{scheduleId} </td>
    <td > - </td>
    <td > 
      {<br>
        "id" : schedule_id ,<br>
        "user_id" : user_id ,<br>
        "detail" : 'schedule_detail',<br>
        "registration_date" : 202x-xx-xx,<br>
        "modification_date" : 202x-xx-xx<br>
      }
    </td>
    <td > 
      2xx : 성공<br> 
      4xx : 실패<br>
    </td>
  </tr><tr>
    <td > 선택 일정 수정 </td>
    <td > PUT </td>
    <td > /api/schedules/{scheduleId} </td>
    <td > 
      {<br>
        "id": schedule_id, <br>
        "user_id" : user_id,<br>
        "password" : password,<br>
        "detail" : 'detail';<br>
      } 
    </td>
    <td >
      {<br>
        "id" : schedule_id ,<br>
        "user_id" : user_id ,<br>
        "detail" : 'schedule_detail',<br>
        "registration_date" : 202x-xx-xx,<br>
        "modification_date" : 202x-xx-xx<br>
      }
    </td>
    <td >
      2xx : 성공<br> 
      4xx : 실패<br></td>
    </td>
  </tr><tr>
    <td > 선택 일정 삭제 </td>
    <td > DELETE </td>
    <td > /api/schedules/{scheduleId} </td>
    <td > - </td>
    <td > - </td>
    <td > 
      2xx : 성공<br> 
      4xx : 실패<br></td>
    </tr>
</table>

> **성공 status code**
>
> >200  :   등록 성공
> 
> **실패 status code**
> 
> >401 : 필수 요소 부족으로 인한 등록 실패
> > 
> >402 : password 조건으로 인한 등록 실패
> >
> >403 : detail 길이초과로 인한 등록 실패
> >
> >411 : 요청 id 값이 없음으로 인한 조회 실패
> >
> >421 : 필수 요소 부족으로 인한 수정 실패
> >
> >422 : password 값이 다름으로 인한 수정 실패
> >
> >423 : detail 길이초과로 인한 수정 실패
> >
> >424 : 요청 id 값이 없음으로 인한 수정 실패
> >
> >425 : 요청 user_id 값이 없음으로 인한 수정 실패
> >
> >444 : 요청 id 값이 없음으로 인한 삭제 실패
>
>---
>- ## 👷‍♂️ ERD
>- ![스크린샷 2024-10-31 오후 7 26 05](https://github.com/user-attachments/assets/f3bbbed3-4b35-462d-9d43-05d4d20a9dc0)
>---
> - ## 🔧 기능 구현 설계
>> 
>> 




