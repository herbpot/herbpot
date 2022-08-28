const scadul = {
    "":"기상",
    "":"식사",
    "":"등교",
    "":"귀사",
    "":"0교시 자습",
    "":"저녁식사",
    "":"1교시 자습",
    "":"2교시 자습",
    "오후 10:00:00":"자유시간",
    
}

const root = document.getElementById("root")

const Title = () => {
    return (<h3>기숙사 일정</h3>)
}

const Next = () => {
    const [nowClock,clockmodifier] = React.useState(0)
    const [anyClock,anymodifier] = React.useState(0)
    const setclock = () => {
        clockmodifier(new Date().toLocaleTimeString())
        anymodifier()
    }
    setInterval(setclock,1000)
    return(
        <div>
            <h1>현재 시각 : {nowClock}</h1>
            <h1>다음 일정까지 남은 시간 : {anyClock}</h1>
        </div>
    )
}

const App = () => {
    return(
    <div>
        <Title />
        <Next />
    </div>
    )

}
ReactDOM.render(<App />,root)