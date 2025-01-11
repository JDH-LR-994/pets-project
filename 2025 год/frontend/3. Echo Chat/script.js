document.addEventListener('DOMContentLoaded', () => {
    const input = document.querySelector('.input');
    const messages = document.querySelector('.messages');
    const sendButton = document.querySelector('.send-button');
    
    loadMessages();

    sendButton.addEventListener('click', handleMessage);
    
    input.addEventListener('keypress', (e) => {
        if (e.key === 'Enter' && !e.shiftKey) {
            e.preventDefault();
            handleMessage();
        }
    });

    function handleMessage() {
        const text = input.value.trim();
        if (text) {
            addMessage(text, 'user');
            
            const typingMessage = addMessage('Бот печатает...', 'typing');
            
            setTimeout(() => {
                typingMessage.remove();
                addMessage(text, 'bot');
            }, 1500);
            
            input.value = '';
        }
    }

    function addMessage(text, type) {
        const message = document.createElement('div');
        message.classList.add('message', type);
        message.textContent = text;
        
        if (type === 'user') {
            message.style.marginLeft = 'auto';
            message.style.backgroundColor = '#95c0ff';
            message.style.color = '#fff';
        } else if (type === 'bot') {
            message.style.marginRight = 'auto';
            message.style.backgroundColor = '#fff';
        } else if (type === 'typing') {
            message.style.marginRight = 'auto';
            message.style.backgroundColor = '#f0f0f0';
            message.style.fontStyle = 'italic';
        }
        
        messages.appendChild(message);
        messages.scrollTop = messages.scrollHeight;
        
        if (type !== 'typing') {
            saveMessage(text, type);
        }
        
        return message;
    }

    function saveMessage(text, type) {
        const messages = JSON.parse(localStorage.getItem('chatMessages') || '[]');
        messages.push({ text, type, timestamp: Date.now() });
        localStorage.setItem('chatMessages', JSON.stringify(messages));
    }

    function loadMessages() {
        const messages = JSON.parse(localStorage.getItem('chatMessages') || '[]');
        messages.forEach(msg => {
            addMessage(msg.text, msg.type);
        });
    }
});
