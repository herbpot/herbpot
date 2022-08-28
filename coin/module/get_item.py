from module.requester import send_request

def get_item(market : str, except_item : str):
    try:
        rtn_list = []
        markets = market.split(',')
        except_item = except_item.split(',')
        url = 'https://api.upbit.com/v1/market/all'
        query = {"isDetails": "false" }
        respons = send_request('GET',url,query,"")
        data = respons.json()

        for data_for in data :
            for market_for in markets:
                if data_for['market'].split('-')[0] == market_for :
                    rtn_list.append(data_for)
        for rtnlist_for in rtn_list :
            for exceptitem_for in except_item:
                for marketFor in markets:
                    if rtnlist_for['market'] == marketFor + '-' + exceptitem_for:
                        rtn_list.remove(rtnlist_for)

        return rtn_list
    except Exception:
        raise