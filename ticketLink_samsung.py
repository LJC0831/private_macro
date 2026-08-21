
import time, os, keyboard, asyncio, ctypes
import pyautogui
from PIL import ImageGrab
import time
import random

# mp3_file = "C:/Users/dlwoc/OneDrive/Desktop/aa.mp3"
# reserve/plan/schedule/1813275131?efw=l  주석하면 개발자도구 됨



mp3_file = "C:/Users/dlwoc/OneDrive/Desktop/aa.mp3" # 알림mp3

## 변수처리
cnt = 2 # 좌석수
# 세션쿠키

# API호출 주소
url ="https://www.ticketlink.co.kr/reserve/plan/schedule/1564601424?evfw=du/hhKuIhUqIhITLJcgIhITa69A5xzX2TruIhITEt1jzWKB2TaQIhIT5hot1lLX5WkQaNoGk6Ktb4xA1D9n1qonKxoZzfoGkwUG5ajA1DInLWKAEnoGK43X2QL=&_=1748599010159"
reserve_button ='//*[@id="reservation"]/div[2]/ul/li[2]/div[3]/a'
 # 개발자도구 우회 예약주소 입력
#reserve_url ="https://www.ticketlink.co.kr/reserve/plan/schedule/892420401?evfw=l"
##
move_duration = random.uniform(0.3, 0.5)
def click():
    ctypes.windll.user32.mouse_event(2, 0, 0, 0, 0)  # 왼쪽 버튼 누르기

    ctypes.windll.user32.mouse_event(4, 0, 0, 0, 0)  # 왼쪽 버튼 떼기


def play_mp3(file_path):
    os.startfile(file_path)

def reserve(cnt):

    print("예매시작")
    pyautogui.moveTo(784, 476, duration=move_duration, tween=pyautogui.easeOutQuad)
    click()
    pyautogui.press('enter')
    #인원수
    pyautogui.moveTo(935, 752, duration=move_duration, tween=pyautogui.easeOutQuad)
    for i in range(cnt):
        click()
    
    pyautogui.moveTo(850, 818, duration=move_duration, tween=pyautogui.easeOutQuad)
    click()
    print("예매됐냐?")
    #play_mp3(mp3_file)
    print("매크로 ` 누르면 재시작")
    each=0
    while each < 1 :
            if keyboard.read_key() == "`":
                break
    return

    

def reserve_check(cnt):
    each = 0
    target_color = (250, 40, 40)  # 찾고자 하는 RGB 색상
    tolerance = 5                # 색상 오차 허용 범위


    # 검색 영역 (left, top, right, bottom)
    #search_box = (900, 430, 938, 686) 
    search_box2 = (900, 451, 938, 480) 
    search_box3 = (900, 589, 938, 603) 
    search_box5 = (900, 634, 938, 638) 
    search_box4 = (900, 681, 938, 685) 
    
    
    

    def is_similar_color(c1, c2, tolerance):
        return all(abs(a - b) <= tolerance for a, b in zip(c1, c2))

    def find_color_and_click(search_box):
        # 영역 스크린샷
        screenshot = ImageGrab.grab(bbox=search_box)
        width, height = screenshot.size

        for x in range(width):
            for y in range(height):
                pixel_color = screenshot.getpixel((x, y))

                if is_similar_color(pixel_color, target_color, tolerance):
                    screen_x = search_box[0] + x
                    screen_y = search_box[1] + y

                    print(f"색상 찾음! 위치: ({screen_x}, {screen_y})")
                    #play_mp3(mp3_file)
                    pyautogui.moveTo(screen_x, screen_y, duration=move_duration, tween=pyautogui.easeOutQuad)
                    click()
                    reserve(cnt)
                    

        print("해당 색상 못 찾음")
        return
      

    find_color_and_click(search_box2)
    find_color_and_click(search_box3)
    find_color_and_click(search_box4)
    find_color_and_click(search_box5)

async def fetch_get(async_session, url, cookie):
    """비동기 GET 요청"""
    async with async_session.get(url, headers={"Cookie": cookie}) as response:
        return await response.json()  # ✅ 수정: JSON 응답을 직접 반환

async def check(cnt):
    if keyboard.is_pressed("esc"):
        print("❌ ESC 키 감지: 종료합니다.")
        raise asyncio.CancelledError()  # 비동기 함수 강제 종료
    # pyautogui.moveTo(927, 390, duration=0.1)
    # click()
    pyautogui.press('F5')
    time.sleep(1)
    pyautogui.moveTo(957, 680, duration=move_duration, tween=pyautogui.easeOutQuad)
    # click()
    # click()
    # click()
    # click()
    pyautogui.click()
    pyautogui.click()
    pyautogui.click()
    pyautogui.click()
    time.sleep(0.1)
    
    reserve_check(cnt)


async def macro():

    each = 0
    print("매크로 ` 누르면 재시작")
    while each < 1 :
            if keyboard.read_key() == "`":
                break
    
    MAX_RETRIES = 1000000  # 최대 재시도 횟수
    retry_count = 0
    while retry_count < MAX_RETRIES:
        await check(cnt)
        retry_count += 1

if __name__ == "__main__":

    asyncio.run(macro())
    

