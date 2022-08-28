import sys
import logging
import traceback
from module.get_item import get_item

from module.loger import *

if __name__ == '__main__':

    try:
        set_loglevel('D')
        item_list = get_item("KRW", "BTC")

        logging.info(item_list)

    except KeyboardInterrupt:
        logging.error('KeyboardInterrupt 에러 발생')
        logging.error(traceback.format_exc())
        sys.exit(1)

    except Exception :
        logging.error('Exception 에러 발생')
        logging.error(traceback.format_exc())
        sys.exit(1)